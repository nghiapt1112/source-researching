package org.nghia.vertx.starter;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class PostHandler {
  public void findAll(RoutingContext rc) {


    rc.response().end(Json.encode(null));
  }

}
