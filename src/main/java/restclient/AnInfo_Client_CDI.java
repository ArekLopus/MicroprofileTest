package restclient;

//	-Using CDI to create a client
//-In this case we need to tell the MicroProfile Rest Client implementation the baseUrl value for the remote endpoint.
//-For that, we use MicroProfile Config. The config property to use is:
//		<fullyQualifiedInterfaceName>/mp-rest/url.
//fe:
//  META-INF/microprofile-config.properties
//	restclient.cdi.ServiceClientIntf/mp-rest/url=http://localhost:8080/MicroprofileTest/res/

//-Rest Endpoint
//	@Path("/greet")
//	public class ServiceClientResource {
//		@GET
//		public String testClient() {
//			return "Greetings from the Rest!";
//		}
//	}

//-Create Rest Client Interface that corresponds to the Servers Endpoint
//	@RegisterRestClient
//	@Path("/greet")
//	public interface ServiceClientIntf {
//	    @GET
//	    String greet();
//	}

//-Inject Rest Client interface
//	@Inject
//	@RestClient
//	private ServiceClientIntf client;



//		MicroProfile Rest Client 1.2 (MP 2.2)
//- NO NEED TO DECLARE BASE URI in MicroProfile Config!!! You can do it in @RegisterRestClient(baseUri="") of Rest Client Interface
//-New `baseUri` property added to `@RegisterRestClient` annotation.
//	@RegisterRestClient(baseUri="http://localhost:9080/testing/res/")

public class AnInfo_Client_CDI {}