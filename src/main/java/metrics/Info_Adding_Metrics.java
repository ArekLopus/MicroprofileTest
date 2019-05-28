package metrics;

//	IMPORTANT! - To see metrics first you MUST run it! otherwise it is not initialized.

//	Adding metrics to your applications
//-To add metrics to your applications, you must create and register metrics with the application registry so that they are known to the system and can be reported on from the /metrics endpoint. You can create and register metrics in the following ways:
// • Using the MetricRegistry directly. By using this method, your application code explicitly creates the metrics and registers them.
// • Using CDI to inject metrics. Metrics are implicitly created by CDI and registered with the application MetricRegistry.
// • Using metrics annotations. Metrics are implicitly created by CDI and registered with the application MetricRegistry.

//-Manual creation;
//	@Inject
//	private MetricRegistry registry;
//
//	Counter counter = registry.counter("Registry_Counter");

//-CDI Injection
//	@Inject
//	@Metric(name="CounterCreatedWithMetricAnnotation", absolute=true)
//	private Counter counter;

//-Annotation
//	@Counted()
//	METHOD, CONSTRUCTOR, TYPE (methods and constructors)

public class Info_Adding_Metrics {}
