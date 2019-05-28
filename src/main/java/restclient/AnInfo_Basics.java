package restclient;

//-The MicroProfile Rest Client provides a type-safe approach to invoke RESTful services over HTTP. 
//-An aim of this specification is to provide a much more natural coding style,
// with the underlying MicroProfile implementation handling the communication between the client and service.

//-Need to create Rest Client Interface that corresponds to the Server Endpoint
//-We create an Interface that will be used to access resource locally

//	Using RestClientBuilder to create a client
//-If RestClient Interface has a @Path annotation, URI for RestClientBuilder is BASE URI!!!
//
//	@RegisterRestClient
//	@Path("client")
//	public interface RestClientIntf {    
//	    @GET
//	    public String testClientItMayBeAnythingJustTheTypeYouWantToReturn();
//	}
//	
//	URI apiUri = new URI("http://localhost:8080/MicroprofileTest/res/");
//	RestClientIntf client = RestClientBuilder.newBuilder()
//		.baseUri(apiUri)
//		.build(RestClientIntf.class);
//
//	@Path("client")
//	public class RestClientResource {
//		@GET
//		public String testClient() {
//			return "Rest Client Testing Works Fine!";
//		}
//	}

//	-Using CDI to create a client
//-In this case we need to tell the MicroProfile Rest Client implementation the baseUrl value for the remote endpoint.
//-For that, we use MicroProfile Config. The config property to use is:
//		<fullyQualifiedInterfaceName>/mp-rest/url.
//fe:
//  META-INF/microprofile-config.properties
//	restclient.cdi.ServiceClientIntf/mp-rest/url=http://localhost:8080/MicroprofileTest/res/
//
//	@RegisterRestClient
//	@Path("/greet")
//	public interface ServiceClientIntf {
//	    @GET
//	    String greet();
//	}
//
//	@Inject
//	@RestClient
//	private ServiceClientIntf client;
//
//	@Path("/greet")
//	public class ServiceClientResource {
//		@GET
//		public String testClient() {
//			return "Greetings from the Rest!";
//		}
//	}
//MicroProfile Rest Client 1.2 (MP 2.2)
//- NO NEED TO DECLARE BASE URI in MicroProfile Config!!! You can do it in @RegisterRestClient(baseUri="") of Rest Client Interface
//-New `baseUri` property added to `@RegisterRestClient` annotation.
//	@RegisterRestClient(baseUri="http://localhost:9080/testing/res/")



//		MicroProfile Rest Client Provider Registration
//-The RestClientBuilder interface extends the Configurable interface from JAX-RS, allowing a user to register custom providers
// while its being built. The behavior of the providers supported is defined by the JAX-RS Client API specification.
//-Below is a list of provider types expected to be supported by an implementation:
// • ClientResponseFilter - Filters of type ClientResponseFilter are invoked in order when a response is received from a remote service.
// • ClientRequestFilter - Filters of type ClientRequestFilter are invoked in order when a request is made to a remote service.
// • MessageBodyReader - The MessageBodyReader interface defined by JAX-RS allows the entity to be read from the API response after invocation.
// • MessageBodyWriter - The MessageBodyWriter interface defined by JAX-RS allows a request body to be written in the request for @POST, @PUT operations, as well as other HTTP methods that support bodies.
// • ParamConverter - The ParamConverter interface defined by JAX-RS allows a parameter in a resource method to be converted to a format to be used in a request or a response.
// • ReaderInterceptor - The ReaderInterceptor interface is a listener for when a read occurs against the response received from a remote service call.
// • WriterInterceptor -The WriterInterceptor interface is a listener for when a write occurs to the stream to be sent on the remote service invocation.
// • ResponseExceptionMapper - The ResponseExceptionMapper is specific to MicroProfile Rest Client. This mapper will take a Response object retrieved via an invocation of a client and convert it to a Throwable, if applicable. 
//-The runtime should scan all of the registered mappers, sort them ascending based on getPriority(), find the ones that can
// handle the given status code and response headers, and invoke them. The first one discovered where toThrowable returns 
// a non-null Throwable that can be thrown given the client method’s signature will be thrown by the runtime.

//	Provider Declaration
//-Providers may be registered via both annotations and the builder pattern.
//-Providers my be registered via the RestClientBuilder's register() methods,
//-Interfaces may use the @RegisterProvider annotation to define classes to be registered as providers
// in addition to providers registered via the RestClientBuilder.
//-Providers may also be registered by implementing the RestClientBuilderListener interface. 
//-This interface is intended as an SPI to allow global provider registration. The impl of this interface must be specified in a
// META-INF/services/org.eclipse.microprofile.rest.client.spi.RestClientBuilderListener file, following the service loader pattern.



public class AnInfo_Basics {}