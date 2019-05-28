package faulttolerance.bulkhead;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

// ab -n 5 -c 5 http://localhost:8080/MicroprofileTest/res/faulttolerance/bulkhead-semaphore-class				// ok
// ab -n 5 -c 5 http://localhost:8080/MicroprofileTest/res/faulttolerance/bulkhead-semaphore-method				// ok
// ab -n 5 -c 5 http://localhost:8080/MicroprofileTest/res/faulttolerance/bulkhead-semaphore-fallback-class		// ok
// ab -n 5 -c 5 http://localhost:8080/MicroprofileTest/res/faulttolerance/bulkhead-semaphore-fallback			//?ok?  
																								//  fallback called, but also throws Excception
//Semaphore style Bulkhead
//-The snippet means the method serviceA applies the Bulkhead policy, which is semaphore approach, limiting the maximum concurrent requests to 5.
//	@Bulkhead(5) 		// maximum 5 concurrent requests allowed
//	public Connection serviceA() {}
//-When using the semaphore approach, on reaching maximum request counter, the extra request will fail with BulkheadException.

//-If a @Fallback is specified, it will be invoked if the BulkheadException is thrown.

@Path("faulttolerance")
public class Bulkhead_Semaphore {
	
	@Inject
	BulkheadBeanMethodLvl bbm;
	
	@Inject
	BulkheadBeanClassLvl bbc;
	
	
	@Path("bulkhead-semaphore-class")
	@GET
	public String testBulkheadClass() throws InterruptedException {
		
		try {
			System.out.println("Value: " + bbc.testMmethod());
			return "Value: " + bbc.testMmethod();
			
		} catch (Exception e) {
			System.out.println("Exception!: " + e.getMessage());
			return "Failed!";
		}
		
	}
	
	
	@Path("bulkhead-semaphore-method")
	@GET
	public String testBulkhead() throws InterruptedException {
		
		try {
			System.out.println("Value: " + bbm.testMmethod());
			return "Value: " + bbm.testMmethod();
			
		} catch (Exception e) {
			System.out.println("Exception!: " + e.getMessage());
			return "Failed!";
		}
		
	}
	
	
	@Path("bulkhead-semaphore-fallback")
	@GET
	public String testBulkheadFallback() throws InterruptedException {
		
		try {
			System.out.println("Value: " + bbm.testMmethodFallback());
			return "Value: " + bbm.testMmethod();
			
		} catch (Exception e) {
			System.out.println("Exception!: " + e.getMessage());
			return "Failed!";
		}
		
	}
	
	
	@Path("bulkhead-semaphore-fallback-class")
	@GET
	public String testBulkheadFallback2() throws InterruptedException {
		
		try {
			System.out.println("Value: " + bbc.testMmethodFallback());
			return "Value: " + bbc.testMmethodFallback();
			
		} catch (Exception e) {
			System.out.println("Exception!: " + e.getMessage());
			return "Failed!";
		}
		
	}
}
