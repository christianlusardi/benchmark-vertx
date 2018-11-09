package it.sysdata.benchmark;

import io.vertx.core.Vertx;
import it.sysdata.benchmark.verticles.BenchmarkVerticle;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
	    vertx.deployVerticle(new BenchmarkVerticle());
	}
}
