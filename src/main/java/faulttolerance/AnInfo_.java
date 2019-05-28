package faulttolerance;

//		Fault Tolerance
//-It is increasingly important to build fault tolerant micro services.
//-Fault tolerance is about leveraging different strategies to guide the execution and result of some logic.
//-Retry policies, bulkheads, and circuit breakers are popular concepts in this area. They dictate whether and when executions
// should take place, and fallbacks offer an alternative result when an execution does not complete successfully.

//	The standard
//-On its 1st version, the FT specification includes the following aspects:
// • Timeout
// • Bulkhead
// • Circuit breaker
// • Retry
// • Fallback
// • Asynchronous
//-This last one looks like the old EJB @Asynchronous annotation, but it isn’t.
// Yes, it allows fast return with the execution on a different thread, but doesn’t need EJBs.

public class AnInfo_ {}