package metrics.metric_annotation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metric;

//http://localhost:8080/metrics/application
//http://localhost:8080/metrics/application/CounterCreatedWithMetricAnnotation
//http://localhost:8080/metrics/application/GaugeResgisteredWithMetricAnnotation

@WebServlet("/metricsMetricAnnotation")
public class Metric_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    @Metric(name="CounterCreatedWithMetricAnnotation", absolute=true)
    private Counter counter;
    
    //It works only to locally produce a Gauge here, NOT as a global injectable bean! (Exception) 
    @Produces
    @Metric(name="GaugeResgisteredWithMetricAnnotation", absolute=true, unit="random")
    @ApplicationScoped
	private Gauge<Double> gauge = new Gauge<Double>() {
    	@Override
		public Double getValue() {
			return ThreadLocalRandom.current().nextDouble(100);
		}
	};
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>MetricRegistry - @Metrics</h3>");
		
		counter.inc(2);
		
		out.println("Counter: " + counter);
		out.println("<br/>Counter: " + counter.getCount());
		
		out.println("<br/>Gauge: " + gauge);
		out.println("<br/>Gauge: " + gauge.getValue());
    }

}
