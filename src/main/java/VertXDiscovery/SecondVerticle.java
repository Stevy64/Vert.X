package VertXDiscovery;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class SecondVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) {
	  System.out.println("2ndVerticle : Ready");
    vertx.eventBus().consumer("connexion2", message -> {
    	System.out.println("2ndVerticle : received message: " + message.body());
    });
  }
}