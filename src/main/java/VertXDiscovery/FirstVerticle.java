package VertXDiscovery;

import io.vertx.core.AbstractVerticle;

public class FirstVerticle extends AbstractVerticle {

	@Override
	public void start () throws Exception {
		System.out.println("Hello World Wide Web");
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("BasicVerticle stopped");
	}
	
}