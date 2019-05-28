package faulttolerance.retry;

import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

// http://localhost:8080/MicroprofileTest/res/faulttolerance/retry

//-After a failed call, @Retry calls it until no exception call or maxRetries is reached.
//	Info: val: 4
//	Retrying as long as maxDuration (180�000ms) isn't breached, and no more than 3 times
//	Info: val: 8
//	Info: val: 6
//	Info: val: 3
//	Info: ---- FALLBACK CALLED ----

//-After retrying to reach no exception call
//	Info: val: 7
//	Info: Retrying as long as maxDuration (180�000ms) isn't breached, and no more than 3 times
//	Info: val: 1

@Path("faulttolerance")
public class RetryResource {
	
	@Path("retry")
	@GET
	@Retry(maxRetries=3, retryOn=Exception.class)
	@Fallback(fallbackMethod="fallback")
	public String testRetry() {
		int val = ThreadLocalRandom.current().nextInt(10);
		System.out.println("val: " + val);
		
		//if (val < 2) throw new RuntimeException("val < 2");
		if (val > 1) throw new RuntimeException("val > 1");
		
		return "val: "+val;
	}
	
	
	public String fallback() {
		String info = "---- FALLBACK CALLED ----";
		System.out.println(info);
		return info;
	}
	
	
}
