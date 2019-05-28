package faulttolerance.bulkhead;

//		Bulkhead
//-The Bulkhead pattern is to prevent faults in one part of the system from cascading to the entire system, which might bring down the whole system. 
//-The implementation is to limit the number of concurrent requests accessing to an instance.
// Therefore, Bulkhead pattern is only effective when applying @Bulkhead to a component that can be accessed from multiple contexts.

//	Semaphore style Bulkhead
//-The below code-snippet means the method serviceA applies the Bulkhead policy, which is semaphore approach, limiting the maximum concurrent requests to 5.
//	@Bulkhead(5) 		// maximum 5 concurrent requests allowed
//	public Connection serviceA() {}
//-When using the semaphore approach, on reaching maximum request counter, the extra request will fail with BulkheadException.

//	Thread pool style Bulkhead
//-The below code-snippet means the method serviceA applies the Bulkhead policy, which is thread pool approach,
// limiting the maximum concurrent requests to 5 and the waiting queue size to 8.
//	@Asynchronous
//	//max 5 concurrent requests allowed, max 8 requests allowed in the waiting queue
//	@Bulkhead(value = 5, waitingTaskQueue = 8)
//	public Future<Connection> serviceA() {}
//-When using the thread pool approach, when a request cannot be added to the waiting queue, BulkheadException will be thrown.

//-When cant process request BulkheadException:
//	org.eclipse.microprofile.faulttolerance.exceptions.BulkheadException: No free work permits.

//-The @Bulkhead annotation can be used together with @Fallback, @CircuitBreaker, @Asynchronous, @Timeout and @Retry.

// If a @Fallback is specified, it will be invoked if the BulkheadException is thrown.
//-If @Retry is used with @Bulkhead, when an invocation fails due to a BulkheadException it is retried after waiting for the delay
// configured on @Retry. If an invocation is permitted to run by the bulkhead but then throws another exception which is handled
// by @Retry, it first leaves the bulkhead, reducing the count of running concurrent requests by 1, waits for the delay
// configured on @Retry, and then attempts to enter the bulkhead again. At this point, it may be accepted, queued
// (if the method is also annotated with @Asynchronous) or fail with a BulkheadException (which may result in further retries).

// IMPORTANT
// @ApplicationScope for @Bulkhead!!!
//-It would not make sense if we used Request or Dependent scope because we would create a new thread pool with each Bulkhead creation 

//-Each @Bulkhead in tread-pool mode creates a new thread pool! ()

public class AnInfo {}