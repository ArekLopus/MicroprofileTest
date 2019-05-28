package restclient;

//-Rest Endpoint
//	@Path("client")
//	public class RestClientResource {
//		@GET
//		public String testClient() {
//			return "Rest Client Testing Works Fine!";
//		}
//	}

//-Create Rest Client Interface that corresponds to the Servers Endpoint
//	@RegisterRestClient
//	@Path("client")
//	public interface RestClientIntf {
//		@GET
//		public String testClientItMayBeAnythingJustTheTypeYouWantToReturn();
//	}

//-Use RestClientBuilder to create a client
//-If RestClient Interface has a @Path annotation, URI for RestClientBuilder is BASE URI!!!
//	URI apiUri = new URI("http://localhost:8080/MicroprofileTest/res/");		// To access http://localhost:8080/MicroprofileTest/res/client
//	RestClientIntf client = RestClientBuilder.newBuilder()
//		.baseUri(apiUri)
//		.build(RestClientIntf.class);
//	System.out.println("Response: " + client.testClientItMayBeAnythingJustTheTypeYouWantToReturn());

public class AnInfo_Client_ {}