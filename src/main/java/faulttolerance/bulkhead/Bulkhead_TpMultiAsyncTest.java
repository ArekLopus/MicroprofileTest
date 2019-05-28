package faulttolerance.bulkhead;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

// Test shows if both bulkheads use different thread pools.
//-We access both endpoints looping, and then put heavy pressure on one of them so it cant serve all requests. Second one should work properly.
//
//-Run multi-1 and multi-2 in PowerShell, then use ab to pressure multi-1. multi-2 should work without errors if in a separate thread pool.
// (curl2 to use external curl (PS has built-in one))
//	while (1) {curl2 http://127.0.0.1:8080/MicroprofileTest/res/faulttolerance/bulkhead-multi-async-1; sleep 1;""}
//	while (1) {curl2 http://127.0.0.1:8080/MicroprofileTest/res/faulttolerance/bulkhead-multi-async-2; sleep 1;""}
//	ab -n 50000 -c 5 http://127.0.0.1:8080/MicroprofileTest/res/faulttolerance/bulkhead-multi-async-1

@Path("faulttolerance")
public class Bulkhead_TpMultiAsyncTest {
	
	@Inject
	BulkheadBeanMultiAsync bma;
	
	
	@Path("bulkhead-multi-async-1")
	@GET
	public String testBulkheadMultiOne() {
		
		Future<String> fut = bma.testMmethodOne();
		
		try {
			System.out.println("Future value: " + fut.get());
			return "Future value: " + fut.get();
		} catch (InterruptedException | ExecutionException e) {
			//System.out.println("Exception!: " + ex.getMessage());
			return "Failed: " + e.getMessage();
		}
			
	}
	
	
	@Path("bulkhead-multi-async-2")
	@GET
	public String testBulkheadMultiTwo() {
		
		Future<String> fut = bma.testMmethodTwo();
		
		try {
			System.out.println("--- Future value: " + fut.get());
			return "--- Future value: " + fut.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("--- Exception!: " + e.getMessage());
			return "Failed: " + e.getMessage();
		}
		
	}
	
}
