package it.sysdata.benchmark.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import it.sysdata.benchmark.core.Core;

public class BenchmarkVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> future) {
		
		
		Router router = Router.router(vertx);
		
		//Hello World
		router.get("/hello").handler(ctx -> Core.helloWorld());
		
		//Compute
		router.get("/compute").handler(ctx -> Core.compute());
		
		//Proxy
		router.get("/proxy").handler(ctx -> Core.proxy());
		
		vertx.createHttpServer().requestHandler(router::accept).listen(8080, http -> isSucceeded(http, future));
	}
	
	
	
	private static void isSucceeded(AsyncResult<HttpServer> http, Future<Void> startFuture) {
		if (http.succeeded()) {
			System.out.println("HttpServer started successfully");
			startFuture.complete();
		} else {
			System.out.println("HttpServer start failed");
			startFuture.fail(http.cause());
		}
	}

}