package metrics.gauge;

//		@Gauge
//-An annotation for marking a method as a gauge.
//-No default MetricUnit is supplied, so the unit must always be specified explicitly.
//-The metric will be registered in the application MetricRegistry. 
//-Given a method annotated with @Gauge like this: 
//  @Gauge(unit = "counter", name = "queueSize")		
//  public int getQueueSize() {
//      return queue.size;
//  }
//-A gauge with the fully qualified class name + queueSize will be created.
//-The gauge value and type is equal to the annotated method return value and type.


//	@Gauge(unit = MetricUnits.PERCENT, name="GaugeTestMethod")				//using MetricUnits

public class AnInfo {}