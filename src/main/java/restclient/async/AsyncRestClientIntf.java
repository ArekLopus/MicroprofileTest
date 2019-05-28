package restclient.async;

import java.util.concurrent.CompletionStage;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterProvider(RestClientAsyncInterceptorFactory.class)		//Works for Servlet
@RegisterRestClient
@Path("/async")
public interface AsyncRestClientIntf {
    
	@GET
    CompletionStage<Response> getCompletionStage();

    @POST
    CompletionStage<String> post(String entity);
    
}