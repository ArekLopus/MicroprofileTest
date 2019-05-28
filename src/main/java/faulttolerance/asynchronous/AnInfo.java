package faulttolerance.asynchronous;

//		@Asynchronous
//-It looks like the old EJB @Asynchronous annotation, but it isn’t. 
//Yes, it allows fast return with the execution on a different thread, but doesn’t need EJBs.

//-Asynchronous means the execution of the client request will be on a separate thread. 
//-This thread should have the correct security context or naming context associated with.

//-Wrap the execution and invoke it asynchronously.
//-Any methods marked with this annotation must return java.util.concurrent.Future.
// Otherwise, org.eclipse.microprofile.faulttolerance.exceptions.FaultToleranceDefinitionException occurs.

//	@Asynchronous
//	public Future<Connection> serviceA() {
//     Connection conn = null;
//     counterForInvokingServiceA++;
//     conn = connectionService();
//     return CompletableFuture.completedFuture(conn);
//  }

//-The @Asynchronous annotation can be used together with @Timeout, @Fallback, @Bulkhead and @Retry.
// Method invocation will occur in a different thread.

//-Uses by default ->  __defaultManagedExecutorService (Payara)
//	Async Test: getAsyncString(), thread: concurrent/__defaultManagedExecutorService-managedThreadFactory-Thread-6

//-Any methods marked with the @Asynchronous annotation must return one of:
// • java.util.concurrent.Future
// • java.util.concurrent.CompletionStage		// Needs MP 2.2 or Fault Tolerance 2.0

public class AnInfo {}