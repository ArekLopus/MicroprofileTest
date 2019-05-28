package faulttolerance.circuit_breaker;

import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

//http://localhost:8080/MicroprofileTest/res/faulttolerance/circuit
//http://localhost:8080/MicroprofileTest/res/faulttolerance/circuit/1

//-When 3 failures occurs during the rolling window of 4 consecutive invocation (4 x 0.75) the circuit is open.
//-The circuit will stay open for 1000ms and then back to half open.
//-After 5 consecutive successful invocations, the circuit will be back to close again.
//-When a circuit is open, A CircuitBreakerOpenException must be thrown. 
//-A @Fallback can be specified and it will be invoked if the CircuitBreakerOpenException is thrown. ??? Not working on Payara

@ApplicationScoped
@Path("/faulttolerance")
public class CircuitBreakerResource {
	
	@Path("/circuit")
	@GET
	@CircuitBreaker(failOn=IllegalStateException.class, successThreshold = 5, requestVolumeThreshold = 4, failureRatio=0.75, delay = 1000)
	public String testCircuit() {
		int val = ThreadLocalRandom.current().nextInt(10);
		System.out.println("val: "+val);
		
		if (val > 3) throw new IllegalStateException("val > 1");
		
		return "val: "+val;
	}
	
	@Path("circuit/{val}")
	@GET
	@CircuitBreaker(failOn=IllegalStateException.class, successThreshold = 5, requestVolumeThreshold = 4, failureRatio=0.75, delay = 1000)
	//@Fallback(FallbackHandlerImpl.class)		//Always invoked, not only after CircuitBreakerOpenException.
	public String testCircuit2(@PathParam("val") int val) {
		System.out.println("Passed value: " + val);

		if (val > 1) throw new IllegalStateException("val > 1");
		
		return "val: "+val;
	}
}
