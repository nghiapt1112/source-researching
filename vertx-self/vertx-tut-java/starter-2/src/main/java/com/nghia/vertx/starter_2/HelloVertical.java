package com.nghia.vertx.starter_2;

import io.vertx.core.AbstractVerticle;

import java.util.UUID;

public class HelloVertical extends AbstractVerticle {
  @Override
  public void start() throws Exception {

    String Vid = UUID.randomUUID().toString();

    vertx.eventBus().consumer("hello.vertx.addr", msg -> {
      msg.reply("Hello Vertx World" + Vid);
    });

    vertx.eventBus().consumer("hello.named.addr", msg -> {
      msg.reply(String.format("Hello %s", (String) msg.body()));
    });
  }
}
