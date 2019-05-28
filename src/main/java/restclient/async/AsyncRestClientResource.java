package restclient.async;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

//http://localhost:8080/MicroprofileTest/res/async
//http://localhost:8080/MicroprofileTest/res/async/test

@Path("async")
public class AsyncRestClientResource {
	
	@Resource
	ManagedExecutorService mes;
	
	
	@GET
	public CompletionStage<Response> getAyncMethod() {
		return CompletableFuture.supplyAsync( () -> {
			String info = "--- Rest Endpoint ('res/async') Returns CompletionStage, thread: " + Thread.currentThread().getName();
			System.out.println(info);
			return Response.ok(info).build();
		}, mes );
	}
	
	
	@Path("test")
	@GET
	public String asyncTestClientGet3() throws URISyntaxException, InterruptedException, ExecutionException {
		
		System.out.println("Testing RestClient ('async/test')");
		
		URI apiUri = new URI("http://localhost:8080/MicroprofileTest/res/");
		AsyncRestClientIntf client = RestClientBuilder.newBuilder()
				//.executorService(mes)
				.baseUri(apiUri)
				.build(AsyncRestClientIntf.class);
		
		Response res = client.getCompletionStage().thenApply(r -> {
			System.out.println("--- @RestClient Response in thread: " + Thread.currentThread().getName());
			return r;
		}).toCompletableFuture().get();
		
		String entity = res.readEntity(String.class);
		
		System.out.println("Endpoint thread: " + Thread.currentThread().getName());
		System.out.println("Response: " + res);
		System.out.println("Entity: " + entity);
		
		return "From /async/test -> " + entity;
	}
	
	
	
	@Path("test2")
	@GET
	public void readsAsyncAndReturnsAsync(@Suspended AsyncResponse ar) throws URISyntaxException {
		
		System.out.println("Testing RestClient...");
		
		URI apiUri = new URI("http://localhost:8080/MicroprofileTest/res/");
		AsyncRestClientIntf client = RestClientBuilder.newBuilder()
				.executorService(mes)
				.baseUri(apiUri)
				.build(AsyncRestClientIntf.class);
		
		client.getCompletionStage().thenAcceptAsync( res -> {
			System.out.println("--- Testing RestClient --- @Path('async/test')");
			System.out.println(res);
			String entity = res.readEntity(String.class);
			System.out.println("@Path('async/test') -> thread: " + Thread.currentThread().getName());
			System.out.println("@Path('async/test') -> entity: " + entity);
			ar.resume("From /async/test2 -> " + entity);
		}, mes);
		
	}
	
}
