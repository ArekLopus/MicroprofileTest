package faulttolerance.timeout;

import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

// http://localhost:8080/MicroprofileTest/res/faulttolerance/timeout

@Path("faulttolerance")
public class TimeoutResource {
	
	int val = 0;
	
	@Path("timeout")
	@GET
	@Timeout(500)
	@Fallback(fallbackMethod="fallback")
	public String testTimeout() throws InterruptedException {
		val = ThreadLocalRandom.current().nextInt(1000);
		System.out.println("val: " + val);
		
		Thread.sleep(val);
		
		return "val: " + val;
	}
	
	
	public String fallback() {
		String info = "---- FALLBACK CALLED ----";
		System.out.println(info);
		return info + ", val = " + val;
	}
}
