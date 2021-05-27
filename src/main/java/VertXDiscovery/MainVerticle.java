package VertXDiscovery;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

public class MainVerticle {
	
	public static void main(String[] args) throws InterruptedException {
		Vertx vertx = Vertx.vertx();
		
		vertx.deployVerticle(new EduticeVerticle(),
				new Handler<AsyncResult<String>>() {
					
					@Override
					public void handle(AsyncResult<String> event) {
						// TODO Auto-generated method stub
						System.out.println("EduticeVerticle : deployment Completed");
						
					}
				});
		
		vertx.deployVerticle(new SecondVerticle(),
				new Handler<AsyncResult<String>>() {
					
					@Override
					public void handle(AsyncResult<String> event) {
						// TODO Auto-generated method stub
						System.out.println("2ndVerticle : deployment Completed");
						
					}
				});
	}
}