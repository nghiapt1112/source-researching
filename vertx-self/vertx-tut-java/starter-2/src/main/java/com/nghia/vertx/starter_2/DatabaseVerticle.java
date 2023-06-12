package com.nghia.vertx.starter_2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

public class DatabaseVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
  }

  Future<Void> runDBMigration() {
    JsonObject dbConfig = config().getJsonObject("db", new JsonObject());
    String url = dbConfig.getString("url", "default value");
    String adminUser = dbConfig.getString("admin_user", "");
    String amdinPasswd = dbConfig.getString("admin_password", "");

    Flyway flyway = Flyway.configure().dataSource(url, adminUser, amdinPasswd).load();
    try {
      flyway.migrate();
//      return Promise.<Void>succeededFuture().
      return null;
    } catch (FlywayException e) {
      throw new RuntimeException(e);
    }
  }
}
