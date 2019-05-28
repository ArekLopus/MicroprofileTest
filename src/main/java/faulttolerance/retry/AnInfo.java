package faulttolerance.retry;

//		Retry Policy
//-In order to recover from a brief network glitch, @Retry can be used to invoke the same operation again.
//-It allows to configure :
// • maxRetries:	the maximum retries			
// • delay: 		delays between each retry		 			• delayUnit: 		the delay unit	
// • maxDuration:	maximum duration to perform the retry for.	• durationUnit:		duration unit
// • jitter:		the random vary of retry delays		 		• jitterDelayUnit:	the jitter unit
// • retryOn:		specify the failures to retry on		 	• abortOn:			specify the failures to abort on

//	Retry usage
//-@Retry can be applied to the class or method level. If the @Retry policy applied on a class level and on a method level
// within that class, the method level @Retry will override the class-level @Retry policy for that particular method.

//-The max retries is 90 but the max duration is 1000ms.  Once the duration is reached, no more retries should be performed.
//	@Retry(maxRetries = 90, maxDuration= 1000)
//	public void serviceB() {}
//-There should be 0-800ms (jitter is -400ms - 400ms) delays between each invocation. Should be at least 4 but no more than 10 retries.
//	@Retry(delay = 400, maxDuration= 3200, jitter= 400, maxRetries = 10)
//	public Connection serviceA() {}
//-Sets retry condition, which means Retry will be performed on IOException.
//	@Retry(retryOn = {IOException.class})
//	public void serviceB() {}

//-The @Retry annotation can be used together with @Fallback, @CircuitBreaker, @Asynchronous, @Bulkhead and @Timeout.
//-A @Fallback can be specified and it will be invoked if the @Retry still fails.
//-If @Retry is used with @Timeout, a retry will only be triggered if TimeoutException or one of its super classes
// are defined in the retryOn attribute of the @Retry.


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

public class AnInfo {}