package metrics_registry;

//		Metric Registries
//-The MetricRegistry is used to maintain a collection of metrics along with their metadata. 
//-There is one shared singleton of the MetricRegistry per scope (application, base, and vendor).
//-When metrics are registered using annotations, they are registered in the application MetricRegistry (thus the application scope).
//-Metrics can be added to or retrieved from the registry either using the @Metric or using the MetricRegistry object directly.

//-A metric is uniquely identified by the MetricRegistry if the MetricID associated with the metric is unique. That is to say, there are
// no other metrics with the same combination of metric name and tags. However, all metrics of the same name must be of the same type
// otherwise an IllegalArgumentException will be thrown. This exception will be thrown during registration.
//-The metadata information is registered under a unique metric name and is immutable. All metrics of the same name must be registered with
// the same metadata information otherwise an "IllegalArgumentException" will be thrown. This exception will be thrown during registration.

//-When injected, the @RegistryType is used as a qualifier to selectively inject either the APPLICATION, BASE, or VENDOR registry.
//-If no qualifier is used, the default MetricRegistry returned is the APPLICATION registry.


//	@RegistryType
//-@RegistryType can be used to retrieve the MetricRegistry for a specific scope.
//-The implementation must produce the corresponding MetricRegistry specified by the RegistryType.


//	Application Metric Registry
//-The implementation must produce the application MetricRegistry when no RegistryType is provided (@Default)
// or when the RegistryType is APPLICATION.
//-Example of the application injecting the application registry
//	@Inject
//	MetricRegistry metricRegistry;
//is equivalent to
//	@Inject
//	@RegistryType(type=MetricRegistry.Type.APPLICATION)
//	MetricRegistry metricRegistry;

//	Base Metric Registry
//-The implementation must produce the base MetricRegistry when the RegistryType is BASE. The base MetricRegistry must contain the required metrics specified in [required-metrics].
//-Example of the application injecting the base registry
//	@Inject
//	@RegistryType(type=MetricRegistry.Type.BASE)
//	MetricRegistry baseRegistry;

//	Vendor Metric Registry
//-The implementation must produce the vendor MetricRegistry when the RegistryType is VENDOR. The vendor MetricRegistry must contain any vendor specific metrics.
//-Example of the application injecting the vendor registry
//	@Inject
//	@RegistryType(type=MetricRegistry.Type.VENDOR)
//	MetricRegistry vendorRegistry;


//	Metadata
//-Metadata is used in MicroProfile-Metrics to provide immutable information about a Metric at registration time.
//-Therefore Metadata is an interface to construct an immutable metadata object. 
//-The object can be built via a MetadataBuilder with a fluent api.

//	Constructors to create Metadata
// • Metadata(Map<String,String> in)
// • Metadata(String name, MetricType type)
// • Metadata(String name, MetricType type, String unit)
// • Metadata(String name, String displayName, String description, MetricType type, String unit)
// • Metadata(String name, String displayName, String description, MetricType type, String unit, String tags)

//-Example of constucting a Metadata object for a Meter and registering it in Application scope
//	Metadata m = new Metadata(
//	    "myMeter",                      // name
//	    "MyMeter",                      // display name
//	    "Example mete",   				// description
//	    MetricType.METER,               // type
//	    MetricUnits.PERCENT);           // units
//	Meter me = new MyMeterImpl();
//	metricRegistry.register(m, me);
//-A default implementation DefaultMetadata is provided in the API for convenience.


//	MetricRegistry abstract class 
//-The registry that stores metrics and their metadata.
//-The MetricRegistry provides methods to register, create and retrieve metrics and their respective metadata.

//	counter(Metadata metadata), counter(String name) 	- Return the registered Counter; or create and register a new Counter 
//	histogram(Metadata metadata), histogram(String name)
//	meter(Metadata metadata), meter(String name)
//	timer(Metadata metadata), timer(String name)
//
//	getCounters(), getCounters(MetricFilter filter), 
//	getGauges(), getGauges(MetricFilter filter), 
//	getHistograms(), getHistograms(MetricFilter filter)
//	getMeters(), getMeters(MetricFilter filter)
//	getTimers(), getTimers(MetricFilter filter)
//
//	getMetrics(), getNames() 			- Returns a set of the names of all the metrics in the registry.
//	getMetadata() 						- Returns a map of all the metadata in the registry and their names.
//
//	name(Class<?> cl, String... names)	- Concatenates a class name and els to form a dotted name, eliding any null values or empty strs.
//	name(String name, String... names) 	- Concatenates elements to form a dotted name, eliding any null values or empty strings.
//
//	register(Metadata metadata, T metric)
//	register(String name, T metric)
//	register(String name, T metric, Metadata metadata) 	- Deprecated. 
//
//	remove(String name)					- Removes the metric with the given name.
//	removeMatching(MetricFilter filter)


public class AnInfo {}