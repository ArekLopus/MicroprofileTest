package metrics.metric_annotation;

//		@Metric
//-CDI injection provides an easy way for metrics to be created and registered to the MetricRegistry.
//-Instead of creating a metadata object, the parameters of the @Metric annotation can be used to assign a metrics metadata.

//-An annotation requesting that a metric be injected or registered.
//-The metric will be registered in the application MetricRegistry.

//-@Metric Parameters: name, displayname, units, tags, description, absolute.
//-This annotation can be used on fields of type Meter, Timer, Counter, and Histogram.

//-Given an injected field annotated with @Metric like this:
//	@Inject
//	@Metric(name="histogram")
//	public Histogram histogram;
// A meter of the field's type will be created and injected into managed objects.
//-It will be up to the user to interact with the metric.

//-This may also be used to register a metric.
//	!!!!It works only to locally produce a Gauge, NOT as a global injectable bean! (Exception)!!!! 
//	@Produces
//	@Metric(name="hitPercentage")
//	@ApplicationScoped
//	Gauge<Double> hitPercentage = new Gauge<Double>() {
//		@Override
//		public Double getValue() {
//			return hits / total;
//		}
//	};

public class Info {}