package it.sysdata.benchmark.core;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Random;

import com.google.gson.Gson;


import it.sysdata.benchmark.models.HttpBinResponse;
import it.sysdata.benchmark.models.Info;
import it.sysdata.benchmark.responses.BaseResponse;

public class Core {

	public static BaseResponse<Object> helloWorld() {
		BaseResponse<Object> baseResponse = new BaseResponse<>();

		baseResponse.setStatusCode(200);
		baseResponse.setMessage("Hello World");
		baseResponse.setData(null);

		return baseResponse;
	}
	
	
	
	public static BaseResponse<Object> compute() {
		BaseResponse<Object> baseResponse = new BaseResponse<>();

		long x = 0, y = 1, z, max;
		Random r = new Random();
		max = 10000 + r.nextInt(500);

		for (int i = 0; i <= max; i++) {
			z = x + y;
			x = y;
			y = z;
		}
		
		baseResponse.setStatusCode(200);
		baseResponse.setMessage("Done");
		baseResponse.setData(null);
		
		return baseResponse;

	}
	
	
	
	public static BaseResponse<Info> proxy() {
		
		BaseResponse<Info> baseResponse = new BaseResponse<>();
		
		Gson gson = new Gson();
				
		HttpClient httpClient = HttpClient.newBuilder()
	               .version(Version.HTTP_2)  // this is the default
	               .build();
		
		HttpRequest request = HttpRequest.newBuilder()
	               .uri(URI.create("https://httpbin.org/get"))
	               .GET()
	               .build();
		

		try {
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
			
			HttpBinResponse httpBinResponse = gson.fromJson(response.body(), HttpBinResponse.class);
			
			Info info = new Info();
			info.setOriginIp(httpBinResponse.getOrigin());
			info.setUserAgent(httpBinResponse.getHeaders().getUserAgent());
			
			baseResponse.setStatusCode(200);
			baseResponse.setMessage("OK");
			baseResponse.setData(info);
			
		} catch (Exception e) {
			baseResponse.setStatusCode(500);
			baseResponse.setMessage(e.getMessage());
			baseResponse.setData(null);
		}
		
		
		return baseResponse;
		
	}
}
