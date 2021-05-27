package VertXDiscovery;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class SecondVerticle extends AbstractVerticle {

  @Override
  public void start() {
	  System.out.println("2ndVerticle : Ready");
    vertx.eventBus().consumer("connexion.querry", message -> {
      JsonObject json = (JsonObject) message.body();
      System.out.println("2ndVerticle : Message Received => " + json.encodePrettily());
    });
  }
}