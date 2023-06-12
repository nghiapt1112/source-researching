package com.nghia.vertx.starter_2;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    // deploy vertx withmultiple instance
//    DeploymentOptions options = new DeploymentOptions().setWorker(true).setInstances(8);

    vertx.deployVerticle(new HelloVertical());

    Router router = Router.router(vertx);
    router.get("/api/v1/hello").handler(this::helloVertx);
    router.get("/api/v1/hello/:name").handler(this::helloParam);


    ConfigStoreOptions configStoreOptions = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "config.json"));
    ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(configStoreOptions);

    ConfigRetriever configRetriever = ConfigRetriever.create(vertx, options);
    configRetriever.getConfig(asyncResult -> handleConfigResult(startPromise, router, asyncResult));
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
