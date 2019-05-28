package metrics.metered;

//		@Metered
//-The meter counts the invocations of the constructor or method and tracks how frequently they are called.
//-An annotation for marking a method, constructor, or class (methods and constructors) as metered.
//-The metric will be registered in the application MetricRegistry. 
//-Given a method annotated with @Metered like this: 
//   @Metered(name = "fancyName")
//   public String fancyName(String name) {
//       return "Sir Captain " + name;
//   }

//-A meter with the fully qualified class name + fancyName will be created and each time
// the #fancyName(String) method is invoked, the meter will be marked.
//-Similarly, the same applies for a constructor annotated with metered.

//JSON response
//	{
//	  "MeteredTestMethod": {
//	    "count": 1,
//	    "fiveMinRate": 0.15576015661428097,
//	    "oneMinRate": 0.05730095937203805,
//	    "fifteenMinRate": 0.18400888292586462,
//	    "meanRate": 0.012335873409326285
//	  }
//	}

public class AnInfo {}