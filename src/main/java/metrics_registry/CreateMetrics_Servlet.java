package metrics_registry;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Gauge;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.MetricUnits;

//http://localhost:8080/metrics/application
//http://localhost:8080/metrics/application/Registry_Counter
//http://localhost:8080/metrics/application/progressGauge

@WebServlet("/metricsRegistryCreate")
public class CreateMetrics_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
	MetricRegistry registry;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>MetricRegistry - Create Metrics</h3>");
		
		//Returns the registered Counter, or create and register a new Counter 
		Counter counter = registry.counter("Registry_Counter");
		counter.inc(4);
		
		//No simple method in MetricRegistry to register a Gauge 
		Gauge<Integer> progressGauge = new Gauge<Integer>() {
			private AtomicInteger counter = new AtomicInteger(23);
		    
			public Integer getValue() {
		        return counter.incrementAndGet();
		    }
		};
		Metadata progressMetadata = new Metadata(
		    "progressGauge",                                // name
		    "Donation Progress",                            // display name
		    "The percentage of the goal achieved so far",   // description
		    MetricType.GAUGE,                               // type
		    MetricUnits.PERCENT);                           // units
		
		//As of version 1.1, use register(Metadata metadata, T metric) instead register(String name, T metric, Metadata metadata)
		registry.register(progressMetadata, progressGauge);
		
		out.println("Counter: " + counter.getCount());
		out.println("<br/>Gauge: " + progressGauge.getValue());
		
    }

}
