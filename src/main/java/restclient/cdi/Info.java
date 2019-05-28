package restclient.cdi;

//	MicroProfile Rest Client CDI Support 
//			(! to run requires the base URL property <fullyQualifiedInterfaceName>/mp-rest/url !)
//-Rest Client interfaces may be injected as CDI beans.
//-@RegisterRestClient - the runtime must create a CDI bean for each interface annotated with it. 
//-@RestClient qualifier differentiates the use as an API call against any other beans registered of the same type.
// Based on the rules of how CDI resolves bean, you are only required to use the qualifier if you have multiple beans of
// the same type. Any injection point or programmatic look up that uses the qualifier
//-RestClient is expected to be resolved by the MicroProfile Rest Client runtime.
//-Below is an example of said interface, with its matching injection point:
//	package com.mycompany.remoteServices;
//	@RegisterRestClient
//	public interface MyServiceClient {
//		@GET
//		@Path("/greet")
//		Response greet();
//	}
//
//	@ApplicationScoped
//	public class MyService {
//		@Inject
//		@RestClient
//		private MyServiceClient client;
//}

//-Likewise, a user can perform programmatic look up of the interface. Here is one example, but any CDI look up should work:
//		MyServiceClient client = CDI.current().select(MyServiceClient.class, RestClient.LITERAL).get();
//-The qualifier is used to differentiate use cases of the interface that are managed by this runtime,
// versus use cases that may be managed by other runtimes.

//-Interfaces are assumed to have a scope of @Dependent unless there is another scope defined on the interface.
//-Implementations are expected to support all of the built in scopes for a bean.
//-Support for custom registered scopes should work, but is not guaranteed.


//-We need to tell the MicroProfile Rest Client implementation the baseUrl value for the remote endpoint.
//-For that, we use MicroProfile Config. The config property to use is:
//	<fullyQualifiedInterfaceName>/mp-rest/url.
//
//	META-INF/microprofile-config.properties
//	restclient.cdi.ServiceClientIntf/mp-rest/url=http://localhost:8080/MicroprofileTest/res/


//	Support for MicroProfile Config
//-For CDI defined interfaces, it is possible to use MicroProfile Config properties to define additional behaviors of the rest intf.
//-Assuming this interface:
//	package com.mycompany.remoteServices;
//	public interface MyServiceClient {
//	    @GET
//	    @Path("/greet")
//	    Response greet();
//	}
//-The values of the following properties will be provided via MicroProfile Config:
// • com.mycompany.remoteServices.MyServiceClient/mp-rest/url: The base URL to use for this service, the equivalent of the baseUrl method. This property (or */mp-rest/uri) is considered required, however impls may have other ways to define these URLs/URIs.
// • com.mycompany.remoteServices.MyServiceClient/mp-rest/uri: The base URI to use for this service, the equivalent of the baseUri method. This property (or */mp-rest/url) is considered required, however impls may have other ways to define these URLs/URIs.
// • com.mycompany.remoteServices.MyServiceClient/mp-rest/scope: The fully qualified classname to a CDI scope to use for injection, defaults to javax.enterprise.context.Dependent as mentioned above.
// • com.mycompany.remoteServices.MyServiceClient/mp-rest/providers: A comma separated list of fully-qualified provider classnames to include in the client, the equivalent of the register method or the @RegisterProvider annotation.
// • com.mycompany.remoteServices.MyServiceClient/mp-rest/providers/com.mycompany.MyProvider/priority will override the priority of the provider for this interface.

//-Implementations may support other custom properties registered in similar fashions or other ways.

//-The url property must resolve to a value that can be parsed by the URL converter required by the MicroProfile Config spec.
//-Likewise, the uri property must resolve to a value that can be parsed by the URI converter.
//-If both the url and uri properties are declared, then the uri property will take precedence.

//-The providers property is not aggregated, the value will be read from the highest property ConfigSource

public class Info {}
