package VertXDiscovery;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class VincentVerticle extends AbstractVerticle {
	
	String pkgName = "opera";
	
	@Override
	public void start (Future<Void> startFuture) throws Exception {		
		vertx.eventBus().publish("connexion", "opera");
        vertx.eventBus().send   ("connexion2", "oper");
        vertx.eventBus().request("connexion3", "firefox", ar -> {
        	  if (ar.succeeded()) {
        		    System.out.println("Received reply : " + ar.result().body());
        		    }
        	  else {
        		  System.out.println("Failure : " + ar.cause().getMessage());
        	  }
        		});
        		
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("VincentVerticle : BasicVerticle stopped");
	}
	
}