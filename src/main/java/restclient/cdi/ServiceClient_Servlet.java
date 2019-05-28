package restclient.cdi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.rest.client.inject.RestClient;

//-We need to tell the MicroProfile Rest Client implementation the baseUrl value for the remote endpoint.
//-For that, we use MicroProfile Config. The config property to use is:
//	<fullyQualifiedInterfaceName>/mp-rest/url.
//
//	META-INF/microprofile-config.properties
//	restclient.cdi.ServiceClientIntf/mp-rest/url=http://localhost:8080/MicroprofileTest/res/

@WebServlet("/restClientCDI")
public class ServiceClient_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    @RestClient
    private ServiceClientIntf client;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Rest Client CDI Support</h3>");
		
		out.println("ServiceClient says: " + client.greet());
		
		ServiceClientIntf client2 = CDI.current().select(ServiceClientIntf.class, RestClient.LITERAL).get();
		out.println("<br/><br/>Programmatic looked up ServiceClient says: " + client2.greet());
		
    }

}
