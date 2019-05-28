package metrics.histogram;

//		Histogram Interface
//-There is also another metric, which does not have an annotation, that is called Histogram.
//-It measures the distribution of values in a stream of data and allows you to measure, not just natural things like the min, mean, max,
// and standard deviation of values, but also quantiles like the median or 95th percentile.

//-To record a value in the histogram, you must call histogram.update(long value) with the value that you want to record.
//-The current state (or snapshot) of the histogram can be retrieved by using getSnapshot(). 
//-Histograms in MicroProfile Metrics only support integer or long values.

//-A histogram provides the following information:
// • Max/Min/Mean values
// • The value at the 50th, 75th, 95th, 98th, 99th, 99.9th percentile
// • A count of the number of values

//-Examples of histograms include the distribution of payload sizes that are retrieved or for an onboarding survey that collects
// the distribution of household income.

//-The following example illustrates a histogram that is used to store the value of donations. This example provides the administrator with an idea of the distribution of donation amounts.
//	Metadata donationDistributionMetadata = new Metadata(
//	    "DonationDistributionHistogram",             // name
//	    "Donation Distribution",                     // display name
//	    "The distribution of the donation amounts ", // description
//	    MetricType.HISTOGRAM,                        // type
//	    "dollars");                                  // units
//	Histogram donationDistribution = registry.histogram(donationDistributionMetadata);
//	public void addDonation(Long amount) {
//	    donationDistribution.update(amount);
//	}

//	{
//	  "DonationDistributionHistogram": {
//	    "p99": 3000,
//	    "min": 1000,
//	    "max": 3000,
//	    "mean": 1813.0573860319537,
//	    "count": 6,
//	    "p50": 2000,
//	    "p999": 3000,
//	    "stddev": 833.0866283919555,
//	    "p95": 3000,
//	    "p98": 3000,
//	    "p75": 3000
//	  }
//	}



//	@POST
//	@Path("/add/{numberOfItems}")
//	public Response addItems(@PathParam("numberOfItems") String numberOfItems) {
//	   Metadata metadata = new Metadata("itemsAdded", MetricType.HISTOGRAM);
//	   Histogram histogram = registry.histogram(metadata);
//	   histogram.update(Long.valueOf(numberOfItems));
//	   return Response.ok().build();
//	}
//Metric Payload for GET /metrics/application/itemsAdded
//"itemsAdded": {
//   "count": 5,
//   "p50": 6,
//   "p75": 6,
//   "p95": 6,
//   "p98": 6,
//   "p99": 6,
//   "p999": 6,
//   "min": 6,
//   "mean": 5.999999999999999,
//   "max": 6,
//   "stddev": 8.881784197001251e-16
//}

public class AnInfo {}