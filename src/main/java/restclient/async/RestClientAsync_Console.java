package restclient.async;

import java.net.URI;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

//-JavaSE client needs RestClient implementation to run.

public class RestClientAsync_Console {
	
	public RestClientAsync_Console() throws Exception {
		
		System.out.println("Testing RestClient...");
		
		//BASE URI! - Interface has @Path("/async") 
		URI apiUri = new URI("http://localhost:8080/MicroprofileTest/res/");
		AsyncRestClientIntf client = RestClientBuilder.newBuilder()
				.baseUri(apiUri)
				.build(AsyncRestClientIntf.class);
		
		Response res = client.getCompletionStage()
			.thenApply(r -> {
				System.out.println("--- Client Side Thread: " + Thread.currentThread().getName());
				return r;
			}).toCompletableFuture().get();
		
		System.out.println("Response: " + res);
		System.out.println("Status: " + res.getStatus());
		System.out.println("Headers: " + res.getHeaders());
		System.out.println("Entity: " + res.readEntity(String.class));
		
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {
		new RestClientAsync_Console();

	}


}
