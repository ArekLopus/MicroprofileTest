package restclient.cdi;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

//http://localhost:8080/MicroprofileTest/res/greet/
//http://localhost:8080/MicroprofileTest/res/greet/test

@Path("/greet")
public class ServiceClientResource {
	
	@GET
	public String testGreetClient() {
		return "Greetings from the Rest!";
	}
	
	
	@Path("test")
	@GET
	public String cdiTestClientGet() throws Exception {
		
		System.out.println("Testing RestClient...");
		
		URI apiUri = new URI("http://localhost:8080/MicroprofileTest/res/");
		ServiceClientIntf client = RestClientBuilder.newBuilder()
				.baseUri(apiUri)
				.build(ServiceClientIntf.class);
		
		System.out.println("Response: " + client.greet());
		
		return "From /greet/test -> " + client.greet();
	}
}
