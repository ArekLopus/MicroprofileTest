package metrics.timed;

//		@Timed
//-The meter counts the invocations of the constructor or method and tracks how frequently they are called.
//-An annotation for marking a method, constructor, or class (methods and constructors) as timed.
//-The metric will be registered in the application MetricRegistry. 
//-Given a method annotated with @Timed like this: 
//	@Timed(name = "fancyName")
//	public String fancyName(String name) {
//  	return "Sir Captain " + name;
//	}

//-A timer with the fully qualified class name + fancyName will be created and each time
// the #fancyName(String) method is invoked, the method's execution will be timed. 

//Works on Payara 5.184
//https://github.com/payara/Payara/issues/2970 - @timed does not measure times, always 0 (only Prometues)

//JSON response
//	{
//	  "TimedTestMethod": {
//	    "fiveMinRate": 0.14424332717941918,
//	    "max": 1851184700,
//	    "count": 2,
//	    "p50": 1851184700,
//	    "p95": 1851184700,
//	    "p98": 1851184700,
//	    "p75": 1851184700,
//	    "p99": 1851184700,
//	    "min": 1850686900,
//	    "fifteenMinRate": 0.17908438459662537,
//	    "meanRate": 0.017641596367838054,
//	    "mean": 1851098258.699114,
//	    "p999": 1851184700,
//	    "oneMinRate": 0.05074590576422439,
//	    "stddev": 188569.30047652652
//	  }
//	}


public class AnInfo {}