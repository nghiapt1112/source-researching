package org.nghia.vertx.starter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.jackson.DatabindCodec;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MainVerticle extends AbstractVerticle {
  private final static Logger LOGGER = Logger.getLogger(MainVerticle.class.getName());

  static {
    LOGGER.info("Customizing the built-in jackson ObjectMapper...");
    var objectMapper = DatabindCodec.mapper();
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
    objectMapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);

    // để serialize date về Java time. vào bên trong để check.
    // explain this code
    JavaTimeModule module = new JavaTimeModule();
    objectMapper.registerModule(module);
  }

  /**
   * Configure logging from logging.properties file.
   * When using custom JUL logging properties, named it to vertx-default-jul-logging.properties
   * or set java.util.logging.config.file system property to locate the properties file.
   */
  private static void setupLogging() throws IOException {
    try (InputStream is = MainVerticle.class.getResourceAsStream("/logging.properties")) {
      LogManager.getLogManager().readConfiguration(is);
    }
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOGGER.log(Level.INFO, "Starting HTTP server...");
    //setupLogging();

    //Create a PgPool instance
    var pgPool = this.pgPool();

    //Creating PostRepository
    var postRepository = PostRepository.create(pgPool);

    //Creating PostHandler
    var postHandlers = PostsHandler.create(postRepository);

    // Initializing the sample data
    // TODO -> apply Flyway for DB initialization and migration instead of this.
    var initializer = DataInitializer.create(pgPool);
    initializer.run();

    // Configure routes

    ConfigStoreOptions fileStore = new ConfigStoreOptions()
      .setType("file").setFormat("json")
      .setConfig(new JsonObject().put("path", "config.json"));

    ConfigRetrieverOptions options = new ConfigRetrieverOptions()
//      .addStore(httpStore)
      .addStore(fileStore)
//      .addStore(sysPropsStore);
      ;
    var router = routes(postHandlers);
    ConfigRetriever retriever = ConfigRetriever.create(vertx, options);
    retriever.getConfig(json -> {
      JsonObject result = json.result();
      // Create the HTTP server
      vertx.createHttpServer()
        // Handle every request using the router
        .requestHandler(req -> req.response().end("OK"))
        .requestHandler(router)
        // Start listening
        .listen(result.getJsonObject("server").getInteger("port"))
        // Print the port
        .onSuccess(server -> {
          startPromise.complete();
          System.out.println("HTTP server started on port " + server.actualPort());
        })
        .onFailure(event -> {
          startPromise.fail(event);
          System.out.println("Failed to start HTTP server:" + event.getMessage());
        });
    });

  }


  //create routes
  private Router routes(PostsHandler handlers) {

    // Create a Router
    Router router = Router.router(vertx);
    // register BodyHandler globally.
    //router.route().handler(BodyHandler.create());
    router.get("/posts").produces("application/json").handler(handlers::all);
    router.post("/posts").consumes("application/json").handler(BodyHandler.create()).handler(handlers::save);
    router.get("/posts/:id").produces("application/json").handler(handlers::get)
      .failureHandler(frc -> {
        Throwable failure = frc.failure();
        if (failure instanceof PostNotFoundException) {
          frc.response().setStatusCode(404).end();
        }
        frc.response().setStatusCode(500).setStatusMessage("Server internal error:" + failure.getMessage()).end();
      });
    router.put("/posts/:id").consumes("application/json").handler(BodyHandler.create()).handler(handlers::update);
    router.delete("/posts/:id").handler(handlers::delete);

    router.get("/hello").handler(rc -> rc.response().end("Hello from my route"));

    return router;
  }

  private PgPool pgPool() {
    PgConnectOptions connectOptions = new PgConnectOptions()
      .setPort(5433)
      .setHost("localhost")
      .setDatabase("blogdb")
      .setUser("postgres")
      .setPassword("postgres");

    // Pool Options
    PoolOptions poolOptions = new PoolOptions().setMaxSize(5).setConnectionTimeout(30);

    // Create the pool from the data object
    PgPool pool = PgPool.pool(vertx, connectOptions, poolOptions);

    return pool;
  }

}
