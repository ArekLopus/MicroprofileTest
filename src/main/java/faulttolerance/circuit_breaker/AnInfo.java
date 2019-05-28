package faulttolerance.circuit_breaker;

//		Circuit Breaker
//-A Circuit Breaker prevents repeated failures, so that dysfunctional services or APIs fail fast. 
//-There are three circuit states:
// • Closed: In normal operation, the circuit is closed. If a failure occurs, the Circuit Breaker records the event.
//   In closed state the requestVolumeThreshold and failureRatio parameters may be configured in order to specify the conditions
//   under which the breaker will transition the circuit to open. If the failure conditions are met, the circuit will be opened.
// • Open: When the circuit is open, calls to the service operating under the circuit breaker will fail immediately.
//   A delay may be configured for the Circuit Breaker. After the specified delay, the circuit transitions to half-open state.
// • Half-open: In half-open state, trial executions of the service are allowed. By default 1 trial call to the service is permitted.
//   If the call fails, the circuit will return to open state. The successThreshold parameter allows the configuration of the number
//   of trial executions that must succeed before the circuit can be closed. After the specified number of successful executions,
//   the circuit will be closed. If a failure occurs before the successThreshold is reached the circuit will transition to open.

//-Note that circuit state transitions will reset the Circuit Breaker’s records. Fe, when the circuit transitions to closed
// a new rolling failure window is created with the configured requestVolumeThreshold and failureRatio.

//	Circuit Breaker Usage
//-A method or a class can be annotated with @CircuitBreaker.
//	@CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio=0.75, delay = 1000)
//	public Connection serviceA() {}
//-When 3 failures occurs during the rolling window of 4 consecutive invocation (4 x 0.75) the circuit is open.
//-The circuit will stay open for 1000ms and then back to half open.
//-After 5 consecutive successful invocations, the circuit will be back to close again.

//-When a circuit is open, A CircuitBreakerOpenException must be thrown. 

//-The @CircuitBreaker annotation can be used together with @Timeout, @Fallback, @Asynchronous, @Bulkhead and @Retry.

//-A @Fallback can be specified and it will be invoked if the CircuitBreakerOpenException is thrown.
//-@Timeout can be configured to fail an operation if it takes longer than the Timeout value to complete.

//IMPORTANT
//@ApplicationScope for @Circuit Breaker!!!

public class AnInfo {}