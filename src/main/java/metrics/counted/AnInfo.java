package metrics.counted;

//		@Counted
//-An annotation for marking a method, constructor, or class (methods and constructors) as counted.
//-The metric will be registered in the application MetricRegistry. 
//-Given a method annotated with @Counted like this: 
//  @Counted(name = "fancyName")
//  public String fancyName(String name) {
//      return "Sir Captain " + name;
//  }
//-A counter with the fully qualified class name + fancyName will be created and each time
//-fancyName(String) method is invoked, the counter will be marked.
//-Similarly, the same applies for a constructor annotated with counted.
// (See monotonic() for how the counter will be incremented). 


//  MicroProfile Metrics 2.0 / MP 3.0
//	https://projects.eclipse.org/projects/technology.microprofile/releases/metrics-2.0
//-Note: This annotation has changed in MicroProfile Metrics 2.0: Counters now always increase monotonically upon invocation.
//	The old behaviour pre 2.0 can now be achieved with @ConcurrentGauge.

//	Old @Counted:
//monotonic - If false (default), the counter is DEcremented when the annotated method returns, counting current invocations of the annotated method.
//			- If true, the counter INncreases monotonically, counting total invocations of the annotated 
//-If you want the counter being increased on each invocation you need to use @Counted(monotonic = true).

public class AnInfo {}