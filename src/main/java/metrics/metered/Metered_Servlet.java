package metrics.metered;

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
//http://localhost:8080/metrics/application/metrics.metered.MeteredTestBean.MeteredTestConstructor
//http://localhost:8080/metrics/application/MeteredTestMethod

@WebServlet("/metricsMetered")
public class Metered_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    @RestClient
    private MetricsTestClientIntf client;
    
    @Inject
    MeteredTestBean mt;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		out.println("Metrics Annotations - @Metered\n");
		
		mt.meteredMethod();
		
		out.println("MeteredTestConstructor: " + client.meteredConstr());
		out.println("MeteredTestMethod: " + client.meteredMethod());
		
    }

}
