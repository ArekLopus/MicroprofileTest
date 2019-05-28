package metrics.histogram;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import metrics.MetricsTestClientIntf;

//	Run Servlet before checking metrics!
//http://localhost:8080/metrics/application
//http://localhost:8080/metrics/application/DonationDistributionHistogram

@WebServlet("/metricsHistogram")
public class Histogram_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    @RestClient
    private MetricsTestClientIntf client;
    
    @Inject
    private MetricRegistry registry;
    
    private Histogram donationDistribution;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Metrics - Histogram</h3>");
		
		Metadata donationDistributionMetadata = new Metadata(
				"DonationDistributionHistogram",             // name
				"Donation Distribution",                     // display name
				"The distribution of the donation amounts ", // description
				MetricType.HISTOGRAM,                        // type
				"dollars");                                  // units
		donationDistribution = registry.histogram(donationDistributionMetadata);
	
		addDonation(1000);
		addDonation(2000);
		addDonation(3000);
		
		
		out.println("DonationDistributionHistogram: " + client.histogramMethod());
    }
    
    public void addDonation(int amount) {
	    donationDistribution.update(amount);
	}
}
