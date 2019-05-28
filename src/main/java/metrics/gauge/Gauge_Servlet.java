package metrics.gauge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import metrics.MetricsTestClientIntf;

//	Run Servlet before checking metrics!
//http://localhost:8080/metrics/application
//http://localhost:8080/metrics/application/metrics.gauge.GaugeTestBean.GaugeTestMethod

@WebServlet("/metricsGauge")
public class Gauge_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    @RestClient
    private MetricsTestClientIntf client;
    
    @Inject
    GaugeTestBean gt;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Metrics Annotations - @Gauge</h3>");
		
		String gauge = client.gaugeMethod();
		
		System.out.println("Gauge val: " + gauge);
		out.println("GaugeTestMethod: " + gauge);
		
    }

}
