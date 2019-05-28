package restclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

//-If RestClient Interface has a @Path annotation, URI for RestClientBuilder is BASE URI!!!
//-To access http://localhost:8080/MicroprofileTest/res/client
//	@Path("client")
//	public interface RestClientIntf {} 

//	URI apiUri = new URI("http://localhost:8080/MicroprofileTest/res/");
//	RestClientIntf client = RestClientBuilder.newBuilder()
//		.baseUri(apiUri)


//-Without @RegisterRestClient Exception:
//	org.jboss.weld.exceptions.IllegalArgumentException: WELD-001508:
//	Cannot create an InjectionTarget from [EnhancedAnnotatedTypeImpl]
// public abstract interface @Path class restclient.RestClientIntf as it is an interface

////-No need to set interface in Configuration, it is only needed for CDI!

@RegisterRestClient
@Path("client")
public interface RestClientIntf {
    
	@GET
	public String testClientItMayBeAnythingJustTheTypeYouWantToReturn();
}