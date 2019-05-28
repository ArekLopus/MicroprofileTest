package restclient.cdi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface ServiceClientIntf {

    @GET
    @Path("/greet")
    String greet();

}