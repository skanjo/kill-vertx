package io.hawsome.kill.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;

public class Phoenix extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    System.out.println("from the ashes..");

    vertx.createHttpServer().requestHandler(event -> {
      final HttpServerResponse resp = event.response();
      resp.setStatusCode(200);
      resp.setChunked(true);
      resp.write("cacaw");
      resp.end();
    }).listen(8080);

    System.out.println("the phoenix rises");
  }

  @Override
  public void stop() throws Exception {
    System.out.println("the phoenix burns");
  }

  public static void main(String[] args) {
    final Vertx v = Vertx.vertx();

    v.deployVerticle(new Phoenix());

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("shut 'er down");
      v.close();
    }));
  }

}
