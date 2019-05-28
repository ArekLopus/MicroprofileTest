package metrics_registry;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Gauge;
import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.Meter;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricRegistry.Type;
import org.eclipse.microprofile.metrics.Timer;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

//http://localhost:8080/metrics/application

@WebServlet("/metricsRegistry")
@SuppressWarnings("rawtypes")
public class MetricRegistry_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    @RegistryType(type=Type.APPLICATION)	// Works without this annotation, APPLICATION is the default type.   
	MetricRegistry registry;
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>MetricRegistry</h3>");
		
		SortedMap<String,Counter> counters = registry.getCounters();
		SortedMap<String,Gauge> gauges = registry.getGauges();
		SortedMap<String, Histogram> histograms = registry.getHistograms();
		SortedMap<String, Meter> meters = registry.getMeters();
		SortedMap<String, Timer> timers = registry.getTimers();
		
		
		out.println("Counters:<br/>");
		counters.keySet().forEach(e-> out.println(e+"<br/>"));
		
		out.println("<br/>Gauges:<br/>");
		gauges.keySet().forEach(e-> out.println(e+"<br/>"));
		
		out.println("<br/>Histograms:<br/>");
		histograms.keySet().forEach(e-> out.println(e+"<br/>"));
		
		out.println("<br/>Meters:<br/>");
		meters.keySet().forEach(e-> out.println(e+"<br/>"));
		
		out.println("<br/>Timers:<br/>");
		timers.keySet().forEach(e-> out.println(e+"<br/>"));
		
		
		SortedSet<String> names = registry.getNames();
		Map<String, Metadata> metadata = registry.getMetadata();
		
		out.println("<br/>Names:<br/>");
		names.forEach(e-> out.println(e+"<br/>"));
		
		out.println("<br/>Metadata - names:<br/>");
		metadata.keySet().forEach(e-> out.println(e+"<br/>"));
		
		out.println("<br/>Metadata - values:<br/>");
		metadata.values().forEach(e-> out.println(e+"<br/>"));
    }

}
