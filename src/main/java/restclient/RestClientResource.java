package restclient;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

//-Resource
//http://localhost:8080/MicroprofileTest/res/client

//-Client to test resource (Does not need the standalone RestClient implementation)
//http://localhost:8080/MicroprofileTest/res/client/test

@Path("client")
public class RestClientResource {
	
	@GET
	public String testClient() {
		return "Rest Client Testing Works Fine!";
	}
	
	
	@Path("test")
	@GET
	public String testClientGet() throws Exception {
		
		System.out.println("Testing RestClient...");
		
		URI apiUri = new URI("http://localhost:8080/MicroprofileTest/res/");
		RestClientIntf client = RestClientBuilder.newBuilder()
				.baseUri(apiUri)
				.build(RestClientIntf.class);
		
		System.out.println("Response: " + client.testClientItMayBeAnythingJustTheTypeYouWantToReturn());
		
		return "From /client/test -> " + client.testClientItMayBeAnythingJustTheTypeYouWantToReturn();
	}
	
}
