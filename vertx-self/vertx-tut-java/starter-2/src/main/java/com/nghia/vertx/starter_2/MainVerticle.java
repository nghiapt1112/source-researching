package com.nghia.vertx.starter_2;

import io.vertx.config.ConfigChange;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.impl.LoggerHandlerImpl;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.LookupOp;
import java.util.UUID;

public class MainVerticle extends AbstractVerticle {
  private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);
  private static final String INSTANCE_ID = UUID.randomUUID().toString();

  private JsonObject currentConfig = config();

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    // TODO:
    //  Get configuration
    //    1.File config
    //    2.Kub config
    vertx.fileSystem().exists("/config.json")
      .onSuccess(this::initConfig);

    //  Setup Router
    Router router = Router.router(vertx);
    //  Logging for all requests
    router.route().handler(this::handleLogger);
    //    RoutingContext Chaining -> TODO
    //  Init session management
    // Khi chạy clusterNode => các node sẽ đc manage bởi vertx_cluster_management , session sẽ đc share qua các node/instance  thông qua vertx_cluster_management
    //
    SessionStore sessionStore = ClusteredSessionStore.create(vertx);
    router.route().handler(SessionHandler.create(sessionStore));

    //  REST APIs
    router.get().handler(this::healthCheck);

    //TODO:  Event bus Bridge
    //     sockJS Websocket

//    router.mountSubRouter("", this.createSockJSEBB());

    //  HTTP Server
    //  Deploy router to server














    // deploy vertx withmultiple instance
//    DeploymentOptions options = new DeploymentOptions().setWorker(true).setInstances(8);

//    vertx.deployVerticle(new HelloVertical());
//
//    Router router = Router.router(vertx);
//    router.get("/api/v1/hello").handler(this::helloVertx);
//    router.get("/api/v1/hello/:name").handler(this::helloParam);
//
//
//    ConfigStoreOptions configStoreOptions = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "config.json"));
//    ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(configStoreOptions);
//
//    ConfigRetriever configRetriever = ConfigRetriever.create(vertx, options);
//    configRetriever.getConfig(asyncResult -> handleConfigResult(startPromise, router, asyncResult));
  }


  /**
   *
   * @return
   */
//  private Router createSockJSEBB() {
//    final SockJSBridgeOptions sockJSBridgeOptions = new SockJSBridgeOptions();
//
//
//
//  }

  private void healthCheck(RoutingContext context) {
    Session session = context.session();
    JsonObject jsonObject = new JsonObject();
    jsonObject.put("id", INSTANCE_ID);
    Integer reqCount = (Integer)session.data().getOrDefault("REQUEST_COUNTER", 0);
    session.data().put("REQUEST_COUNTER", reqCount + 1);
    jsonObject.put("REQUEST_COUNTER", reqCount + 1);
    context.response().setStatusCode(200).setStatusMessage("OK")
      .putHeader("Content-Type", "application/json")
      .end(jsonObject.encodePrettily())
    ;
    LOGGER.info("Pod info {}", jsonObject.encodePrettily());
  }

  private void handleLogger(RoutingContext context) {
    LOGGER.info("Request: {}", context.request().path());
    // TODO: can implement some Filter level from here like SpringFilter
    context.next();
  }

  private void initConfig(Boolean jsonConfig) {
    ConfigRetrieverOptions confOption = new ConfigRetrieverOptions();
    if (jsonConfig) {
      confOption.addStore(
        new ConfigStoreOptions()
          .setType("file").setFormat("json")
          .setConfig(new JsonObject().put("path", "./config.json"))
      );
    }
    if (System.getenv().containsKey("KUBERNETES_NAMESPACE")) {
      confOption.addStore(
        new ConfigStoreOptions()
          .setType("configmap")
          .setConfig(new JsonObject()
            .put("namespace", System.getenv().getOrDefault("KUBERNETES_NAMESPACE", "default"))
            .put("name", "dmeodmeodemo")
          )
      );
    }
    ConfigRetriever.create(vertx, confOption).listen(this::loadNewConfig);
  }

  private void loadNewConfig(ConfigChange configChange) {
    this.currentConfig.mergeIn(configChange.getNewConfiguration());
  }

  private void handleConfigResult(Promise<Void> startPromise, Router router, AsyncResult<JsonObject> asyncResult) {
    if (asyncResult.succeeded()) {
//      JsonObject config = asyncResult.result();
      JsonObject httpObject = config().getJsonObject("http");
      vertx.createHttpServer().requestHandler(router).listen(httpObject.getInteger("port"), http -> {
        if (http.succeeded()) {
          startPromise.complete();
          System.out.println("HTTP server started on port 8888");
        } else {
          startPromise.fail(http.cause());
        }
      });
    } else {
      System.out.println("Loi roi");
    }
  }

  private void helloParam(RoutingContext context) {
    String name = context.pathParam("name");
//    context.request().response().end(String.format("Hello %s", name));

    vertx.eventBus().request("hello.named.addr", name, reply -> {
      context.request().response().end((String) reply.result().body());
    });
  }

  private void helloVertx(RoutingContext routingContext) {
//    routingContext.request().response().end("Hello Vertx");
    vertx.eventBus().request("hello.vertx.addr", "", reply -> {
      routingContext.request().response().end((String) reply.result().body());
    });
  }
}
