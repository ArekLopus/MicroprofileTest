package faulttolerance.fallback;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.RestClientDefinitionException;

// http://localhost:8080/MicroprofileTest/res/faulttolerance/fallback

@Path("faulttolerance")
public class FallbackResource {
	
	@Path("fallback")
	@GET
	@Retry(maxRetries=3)
	@Fallback(FallbackHandlerImpl.class)
	//@Fallback(fallbackMethod="fallback")
	public String testFallback() throws InterruptedException, IllegalStateException, RestClientDefinitionException, MalformedURLException, URISyntaxException {
		int val = 2;
		
		System.out.println("val > 1");
		
		if (val > 1) throw new RuntimeException("val > 1");
		
		return "Fallback not initialized";
	}
	
	
	public String fallback() {
		String info = "---- FALLBACK (method) CALLED ----";
		System.out.println(info);
		return info;
	}
	
	
}
