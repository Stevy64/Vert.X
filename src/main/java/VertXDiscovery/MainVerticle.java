package VertXDiscovery;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

public class MainVerticle {
	
	public static void main(String[] args) throws InterruptedException {
		Vertx vertx = Vertx.vertx();
		
		vertx.deployVerticle(new HttpVerticle(),
				new Handler<AsyncResult<String>>() {
					
					@Override
					public void handle(AsyncResult<String> event) {
						// TODO Auto-generated method stub						
					}
				});
		
		vertx.deployVerticle(new SecondVerticle(),
				new Handler<AsyncResult<String>>() {
					
					@Override
					public void handle(AsyncResult<String> event) {
						// TODO Auto-generated method stub						
					}
				});
		
		Thread.sleep(3000);
		vertx.deployVerticle(new VincentVerticle(),
				new Handler<AsyncResult<String>>() {
					
					@Override
					public void handle(AsyncResult<String> event) {
						// TODO Auto-generated method stub						
					}
				});
		
	}
}