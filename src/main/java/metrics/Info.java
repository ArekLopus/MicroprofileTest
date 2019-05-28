package metrics;

//		Metrics
//-To ensure reliable operation of software it is necessary to monitor essential system parameters.
//-This enhancement proposes the addition of well-known monitoring endpoints and metrics for each process adhering to the MP standard.
//-This proposal does not talk about health checks. There is a separate specification for Health Checks.

//	Difference to health checks
//-Health checks are primarily targeted at a quick yes/no response to the question "Is my application still running ok?".
//-Modern systems that schedule the starting of apps (e.g. Kubernetes) use this information to restart the application if the answer is 'no'.

//-Metrics on the other hand can help to determine the health.
//-Beyond this they serve to pinpoint issues, provide long term trend data for capacity planning and pro-active discovery of issues
// (e.g. disk usage growing without bounds).
//-Metrics can also help those scheduling systems decide when to scale the application to run on more or fewer machines.

// • Counters are a metric that is used to keep an incremental or a decremental count. 
// • Gauges represent metrics that are sampled to obtain their value.
// • Meters are used to track throughput.
// • Histograms are used to store the distribution of values.
// • Timers are used to aggregate timing durations, in nanoseconds, and provide duration and throughput statistics.


//	Application Metrics Programming Model
//-MicroProfile Metrics provides a way to register App-specific metrics to allow applications to expose metrics in the application scope
//-Metrics and their metadata are added to a Metric Registry upon definition and can afterwards have their values set and retrieved via
// the Java-API and also be exposed via the REST-API.
//-It is possible to use the non-annotations API, but using the annotations will generally be easier for developers.

//	Responsibility of the MicroProfile Metrics implementation
// • The impl must scan the app at deploy time for Annotations and register the Metrics and  their metadata in the app MetricsRegistry.
// • The impl must watch the annotated objects and update internal data structures when the values of the annotated objects change.
// • The impl must expose the values of the objects registered in the MetricsRegistry via REST-API as described in [rest-api].
// • Metrics registered via non-annotations API need their values be set via updates from the application code.
// • The impl must flag duplicate metrics upon registration and reject the duplicate unless the metric is explicitly marked as reusable upon first registration and in all subsequent registrations.
//    • A duplicate metric is a metric that has the same scope and name as an existing one.
//    • The implementation must throw an IllegalArgumentException when the metric is rejected.
//    • It is not allowed to reuse a metric (name) for metrics of different types.
//      The implementation must throw an IllegalArgumentException if such a mismatch is detected.
//    • See reusing of metrics for more details.
// • The impl must flag and reject metrics upon registration if the metadata information being registered is not equivalent to the metadata information that has already been registered under the given metric name (if it already exists).
//    • All metrics of a given metric name must be associated with the same metadata information
//    • The implementation must throw an IllegalArgumentException when the metric is rejected.


//	Annotations
//-All Annotations are in the org.eclipse.microprofile.metrics.annotation package
//	Note:
//-These annotations include interceptor bindings as defined by the Java Interceptors specification.
//-That implies only managed beans whose bean types are proxyable can be instrumented using the Metrics annotations.

//-The following Annotations exist, see below for common fields:
//	Annotation			Applies to	Description																			Default Unit
//	@Counted			M, C, T		Denotes a counter, which counts the invocations of the annotated object.			MetricUnits.NONE
//	@ConcurrentGauge  	M, C, T		Denotes a gauge which counts the parallel invocations of the annotated object.		MetricUnits.NONE
//	@Gauge				M			Denotes a gauge, which samples the value of the annotated object.					none Must be supplied by user
//	@Metered			M, C, T		Denotes a meter, which tracks the frequency of invocations of the annotated object.	MetricUnits.PER_SECOND
//	@Timed				M, C, T		Denotes a timer, which tracks duration of the annotated object.						MetricUnits.NANOSECONDS
//	@Metric				M, F, P		An annotation that contains the metadata information when requesting a metric to	MetricUnits.NONE
//									be injected or produced. This annotation can be used on fields of type Meter,
//									Timer, Counter, and Histogram. For Gauge, the @Metricannotation can only be used
//									on producer methods/fields.
//	(C=Constructor, F=Field, M=Method, P=Parameter, T=Type)

//	Annotation		Description																					Default
//	@RegistryType	Qualifies the scope of Metric Registry to inject when injecting a MetricRegistry.			application (scope)

//	Fields
//-All annotations (Except RegistryType) have the following fields that correspond to the metadata fields described in [meta-data-def].
// • name (String) 			- Optional. Sets the name of the metric. If not explicitly given the name of the annotated object is used.
// • absolute (boolean) 	- If true, uses the given name as the absolute name of the metric. If false, prepends the package name
//							  and class name before the given name. Default value is false.
// • displayName (String ) 	- Optional. A human readable display name for metadata.
// • description (String) 	- Optional. A description of the metric.
// • unit (String) 			- Unit of the metric. For @Gauge no default is provided. Check the MetricUnits class for a set of pre-defined units.
// • tags (String[]) 		- Optional. Array of Strings in the <key>=<value> format to supply special tags to a metric.
// • reusable (boolean) 	- Denotes if a metric with a certain name can be registered in more than one place. Does not apply to gauges.

//Note:
//-Implementors are encouraged to issue warnings in the server log if metadata is missing.
//-Implementors MAY stop the deployment of an application if Metadata is missing.


public class Info {}