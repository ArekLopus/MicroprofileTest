package faulttolerance.timeout;

//		Timeout
//-Timeout prevents from the execution from waiting forever.
//-It is recommended that a microservice invocation should have timeout associated with.

//	Timeout Usage
//-A method or a class can be annotated with @Timeout, which means the method(s) under the class will have Timeout policy applied.
//	@Timeout(400) 		// timeout is 400ms
//	public Connection serviceA() {}
//-The above code-snippet means the method serviceA applies the Timeout policy, which is to fail the execution if
// the execution takes more than 400ms to complete even if it successfully returns.

//-When a timeout occurs, A TimeoutException must be thrown.

//-The @Timeout annotation can be used together with @Fallback, @CircuitBreaker, @Asynchronous, @Bulkhead and @Retry.

//-A @Fallback can be specified and it will be invoked if the TimeoutException is thrown.
//-If @Timeout is used together with @Retry, the TimoutException will trigger the retry. 
//-When @Timeout is used with @CircuitBreaker and if a TimeoutException occurs, the failure will contribute towards the circuit open.

//-When @Timeout is used with @Asynchronous, then a separate thread will be spawned to perform the work in the annotated method,
// while a Future is returned on the main thread. If the work on the spawned thread does timeout, then a get() call to the Future
// on the main thread should throw an ExecutionException that wraps a fault tolerance TimeoutException.

//-When @Timeout is used without @Asynchronous, the current thread will be interrupted with a call to Thread.interrupt() on reaching
// the specified timeout duration. The interruption will only work in certain scenarios. The interruption will not work when:
// • The thread is blocked on blocking I/O (database, file read/write), an exception is thrown only in case of waiting for a NIO channel
// • The thread isn’t waiting (CPU intensive task) and isn’t checking for being interrupted
// • The thread will catch the interrupted exception (with a general catch block) and will just continue processing, ignoring the interrupt
//-In the above situations, it is impossible to suspend the execution. The execution thread will finish its process. If the execution
// takes longer than the specified timeout, the TimeoutException will be thrown and the execution result will be discarded.


public class AnInfo {}