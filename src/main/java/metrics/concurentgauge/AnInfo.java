package metrics.concurentgauge;

//		@ConcurrentGauge - MicroProfile Metrics 2.0 / MP 3.0 - Doesnt work on Payara 5.191, wlp 19.0.0.4 yet.
// https://projects.eclipse.org/projects/technology.microprofile/releases/metrics-2.0
// https://dzone.com/articles/microprofile-what-you-need-to-know

//	It is previous default behavior of @Counted.
//-Note: This annotation has changed in MicroProfile Metrics 2.0: Counters now always increase monotonically upon invocation.
//The old behaviour pre 2.0 can now be achieved with @ConcurrentGauge.

//-An annotation for marking a method, constructor, or type as a parallel invocation counted.
//-The semantics is such that upon entering a marked item, the parallel count is increased by one and upon exit again decreased by one.
//-The purpose of this annotation is to gauge the number of parallel invocations of the marked methods or constructors.

//-When a constructor is annotated, the implementation must register gauges, representing the current, previous minute maximum,
// and previous minute minimum values for the constructor using the Annotated Naming Convention.

//-When a method is annotated, the implementation must register gauges, representing the current, previous minute maximum,
// and previous minute minimum values for the method using the Annotated Naming Convention.

//-When a type/class is annotated, the implementation must register gauges, representing the current, previous minute maximum,
// and previous minute minimum values for each of the constructors and methods using the Annotated Naming Convention.

public class AnInfo {}