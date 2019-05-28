package faulttolerance;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

// curl http://localhost:8080/MicroprofileTest/res/faulttolerance/composite

//-Composite example where we retry 2 times after a timeout of 1 second.
//-We fallback to the MovieFindAllFallbackHandler if we still get exceptions after the last retry.
@Path("/faulttolerance")
public class ATimeoutRetryFallback_Resource {
	
	@Path("/composite")
    @GET
    @Timeout											// default 1 sec.
    @Retry(delay = 1000, maxRetries = 2)				// default to all Exception
    @Fallback(ATimeoutRetryFallbackFallbackHandler.class)
    public List<String> findAll() {

        System.out.println("findAll() called.");
        
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (final InterruptedException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return Arrays.asList("The Terminator", "The Matrix", "Rambo");
    }
}