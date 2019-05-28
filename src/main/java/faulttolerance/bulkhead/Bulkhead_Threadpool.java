package faulttolerance.bulkhead;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

// ab -n 5 -c 5 http://localhost:8080/MicroprofileTest/res/faulttolerance/bulkhead-threadpool-class				// ok
// ab -n 5 -c 5 http://localhost:8080/MicroprofileTest/res/faulttolerance/bulkhead-threadpool-method			// ok
// ab -n 5 -c 5 http://localhost:8080/MicroprofileTest/res/faulttolerance/bulkhead-threadpool-fallback			// ok
// ab -n 5 -c 5 http://localhost:8080/MicroprofileTest/res/faulttolerance/bulkhead-threadpool-fallback-class	// ok

//Thread pool style Bulkhead
//-The snippet means the method serviceA applies the Bulkhead policy, which is thread pool approach,
// limiting the maximum concurrent requests to 5 and the waiting queue size to 8.
//	@Asynchronous
//	//max 5 concurrent requests allowed, max 8 requests allowed in the waiting queue
//	@Bulkhead(value = 5, waitingTaskQueue = 8)
//	public Future<Connection> serviceA() {}
//-When using the thread pool approach, when a request cannot be added to the waiting queue, BulkheadException will be thrown.

//-If a @Fallback is specified, it will be invoked if the BulkheadException is thrown.

@Path("faulttolerance")
public class Bulkhead_Threadpool {

	@Inject
	BulkheadBeanAsyncClassLvl abc;
	
	@Inject
	BulkheadBeanAsyncMethodLvl abm;
	
	
	@Path("bulkhead-threadpool-class")
	@GET
	public String testBulkheadClass() {
		
		Future<String> fut = abc.testMmethod();
		
		try {
			System.out.println("Future value: " + fut.get(10, TimeUnit.SECONDS));
			return "Future value: " + fut.get();
		} catch (InterruptedException | ExecutionException | TimeoutException ex) {
			System.out.println("Exception!: " + ex.getMessage());
			return "Failed: " + ex.getMessage();
		}
		
	}
	
	
	@Path("bulkhead-threadpool-method")
	@GET
	public String testBulkheadMethod() {
		
		Future<String> fut = abm.testMmethod();
		
		try {
			System.out.println("Future value: " + fut.get(10, TimeUnit.SECONDS));
			return "Future value: " + fut.get();
		} catch (InterruptedException | ExecutionException | TimeoutException ex) {
			System.out.println("Exception!: " + ex.getMessage());
			return "Failed: " + ex.getMessage();
		}
		
	}
	
	
	@Path("bulkhead-threadpool-fallback")
	@GET
	public String testBulkheadClassFallback() {
		
		Future<String> fut = abm.testMmethodFallback();
		
		try {
			System.out.println("Future value: " + fut.get(10, TimeUnit.SECONDS));
			return "Future value: " + fut.get();
		} catch (InterruptedException | ExecutionException | TimeoutException ex) {
			System.out.println("Exception!: " + ex.getMessage());
			return "Failed: " + ex.getMessage();
		}
		
	}
	
	@Path("bulkhead-threadpool-fallback-class")
	@GET
	public String testBulkheadClassFallbackClass() {
		
		Future<String> fut = abm.testMmethodFallback();
		
		try {
			System.out.println("Future value: " + fut.get(10, TimeUnit.SECONDS));
			return "Future value: " + fut.get();
		} catch (InterruptedException | ExecutionException | TimeoutException ex) {
			System.out.println("Exception!: " + ex.getMessage());
			return "Failed: " + ex.getMessage();
		}
		
	}
}
