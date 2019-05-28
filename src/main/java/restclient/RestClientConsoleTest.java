package restclient;

import java.net.URI;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

//-JavaSE client needs RestClient implementation to run. (DONT DEPLOY when this impl is on -> libs conflicts!)
//-No need to set interface in Configuration, it is only needed for CDI!
public class RestClientConsoleTest {

	public RestClientConsoleTest() throws Exception {
		
		System.out.println("Start client testing...");
		
		URI apiUri = new URI("http://localhost:8080/MicroprofileTest/res/");
		
		RestClientIntf client = RestClientBuilder.newBuilder()
				.baseUri(apiUri)
				.build(RestClientIntf.class);
		
		System.out.println(client.testClientItMayBeAnythingJustTheTypeYouWantToReturn());
		
	}

	public static void main(String[] args) throws Exception {
		new RestClientConsoleTest();

	}


}
