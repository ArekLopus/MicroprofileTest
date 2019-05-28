package health;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

//-Interface used for MP Rest Client to test health
@RegisterRestClient
public interface LivenessCheckOutputIntf {
	
    @GET
    @Path("/health")
    String checkHealth();
    
}