package com.nghia.vertx.inaction.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloVertical extends AbstractVerticle {
  private final Logger logger = LoggerFactory.getLogger(HelloVertical.class);
  private long counter = 1;

  @Override
  public void start() throws Exception {
    vertx.setPeriodic(5000, id -> {
      logger.info("xin chao heheh");
    });

    vertx.createHttpServer()
      .requestHandler(req -> {
        logger.info("request #{} from {},", counter++, req.remoteAddress().host());
      }).listen(8080);
    ;

    logger.info("Vertx deployued on 8080 port");
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HelloVertical());
  }

}
