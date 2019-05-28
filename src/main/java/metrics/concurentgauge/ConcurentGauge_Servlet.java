package metrics.concurentgauge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//		Needs MicroProfile Metrics 2.0 / MP 3.0
//	Run Servlet before checking metrics!
//http://localhost:8080/metrics/application
//http://localhost:8080/metrics/application/metrics.concurentgauge.ConcurentGaugeTestBean.ConcurentGaugeTestMethod

@WebServlet("/metricsConcurentGauge")
public class ConcurentGauge_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    ConcurentGaugeTestBean cgt;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Metrics Annotations - @ConcurentGauge</h3>");
		
		cgt.concurentGaugeMethod();
		
    }

}
