package abc;

//	@CircuitBreaker
//-@CircuitBreaker, @Fallback is always invoked, not only after CircuitBreakerOpenException (spec).
//	@CircuitBreaker(failOn=IllegalStateException.class, successThreshold = 5, requestVolumeThreshold = 4, failureRatio=0.75, delay = 1000)
//	@Fallback(FallbackHandlerImpl.class)		//Always invoked, not only after CircuitBreakerOpenException.
//
//-IMPORTANT - @ApplicationScope for @CircuitBreaker to work properly!!!


//	@Bulkhead - IMPORTANT - @ApplicationScope for @Bulkhead !!!
//-It would not make sense if we used Request or Dependent scope because we would create a new thread pool with each Bulkhead creation 


//	@ConcurrentGauge
//-Metrics 2.0 / Microprofile 3.0


//	OpenAPI
//On Payara http://localhost:8080/openapi shows wrongly servers (http - 8181 and https - 8080):
//- url: http://192.168.0.46:8181/MicroprofileTest
//  description: Default Server.
//- url: https://192.168.0.46:8080/MicroprofileTest
//  description: Default Server.
//and it doesnt work properly for org.microprofile-ext.openapi-ext - swagger-ui (Works ok on WLP)
//
//-It can be overridden as a config property
//	mp.openapi.servers=http://localhost:8080/MicroprofileTest/
//after that:
//	servers:
//	- url: http://localhost:8080/MicroprofileTest/

public class Info_Problems {}
