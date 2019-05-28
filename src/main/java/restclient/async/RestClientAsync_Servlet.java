package restclient.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@WebServlet("/restClientAsync")
public class RestClientAsync_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    @RestClient
    private AsyncRestClientIntf client;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Rest Client Asynchronous</h3>");
		
		try {
			
			Response res = client.getCompletionStage().thenApply(r -> {
				out.println("@RestClient response, thread: " + Thread.currentThread().getName());
				return r;
			}).toCompletableFuture().get();
			
			String entity = res.readEntity(String.class);
			System.out.println(entity);
			
			out.println("<br/><br/>ServiceClient Response: " + res);
			out.println("<br/><br/>ServiceClient Entity: " + entity);
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
    }

}
