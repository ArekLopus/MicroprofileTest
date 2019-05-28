package metrics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

//-Interface used for MP Rest Client to test metrics
//-Because it uses CDI it needs to be added to the Configuration, here to:
//	META-INF/microprofile-config.properties -> metrics.MetricsClientIntf/mp-rest/url=http://localhost:8080/metrics/application/
@RegisterRestClient
public interface MetricsTestClientIntf {
	
	//@Counted
    @GET
    @Path("/metrics.counted.CountedTestBean.CountedTestConstructor")
    String countedConstr();
    
    @GET
    @Path("/CountedTestMethod")
    String countedMethod();
    
    
    //@Gauge
    @GET
    @Path("/metrics.gauge.GaugeTestBean.GaugeTestMethod")
    String gaugeMethod();
    
    
    //Histogram
    @GET
    @Path("/DonationDistributionHistogram")
    String histogramMethod();
    
    
    //@Metered
    @GET
    @Path("/metrics.metered.MeteredTestBean.MeteredTestConstructor")
    String meteredConstr();
    
    @GET
    @Path("/MeteredTestMethod")
    String meteredMethod();
    
    
    //@Timed
    @GET
    @Path("/metrics.timed.TimedTestBean.TimedTestConstructor")
    String timedConstr();
    
    @GET
    @Path("/TimedTestMethod")
    String timedMethod();
    
    
}