package faulttolerance.fallback;

//		Fallback
//-Fallbacks are invoked once a Retry or CircuitBreaker has failed enough times.
//-For a Retry, Fallback is handled any time the Retry would exceed its maximum number of attempts.
//-For a CircuitBreaker, it is invoked any time the method invocation fails. When the Circuit is open, the Fallback is always invoked.

//	Fallback usage
//-A method can be annotated with @Fallback, which means the method will have Fallback policy applied. 2 ways to specify fallback:
//	 • Specify a FallbackHandler class		 • Specify the fallbackMethod

//	Specify a FallbackHandler class
//-If a FallbackHandler is registered for a method returning a different type than the FallbackHandler would return,
// then the container should treat as an error and deployment fails.
//-FallbackHandlers are meant to be CDI managed, and should follow the life cycle of the scope of the bean.
//	    @Retry(maxRetries = 1)
//	    @Fallback(StringFallbackHandler.class)
//	    public String serviceA() {}
//-The above code snippet means when the method failed and retry reaches its maximum retry, the fallback operation will be performed.
//-The method StringFallbackHandler.handle(ExecutionContext context) will be invoked. The return type of handle(ExecutionContext ctx)
// must be String. Otherwise, the FaultToleranceDefinitionException exception will be thrown.

//	Specify the fallbackMethod
//-This is used if the fallbackMethod is on the same class as the method to call fall back.
//	    @Retry(maxRetries = 2)
//	    @Fallback(fallbackMethod= "fallbackForServiceB")
//	    public String serviceB() {}
//
//	    private String fallbackForServiceB() {
//	        return "myFallback";
//	    }
//-The above code snippet means when the method failed and retry reaches its maximum retry, the fallback operation will be performed.
//-The method fallbackForServiceB will be invoked. The return type of fallbackForServiceB must be String and the argument list 
// for fallbackForServiceB must be the same as ServiceB. Otherwise, the FaultToleranceDefinitionException exception will be thrown.

//-The parameter value and fallbackMethod on @Fallback cannot be specified at the same time.
// Otherwise, the FaultToleranceDefinitionException exception will be thrown.

//-The fallback should be triggered when an exception occurs.
// For instance, BulkheadException, CircuitBreakerOpenException, TimeoutException should trigger the fallback.

public class AnInfo {}