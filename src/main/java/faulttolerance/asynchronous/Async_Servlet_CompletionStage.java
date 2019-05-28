package faulttolerance.asynchronous;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Needs MP 2.2 or Fault Tolerance 2.0
// http://localhost:8080/MicroprofileTest/faulttoleranceAsynchronousCS
@WebServlet("/faulttoleranceAsynchronousCS")
public class Async_Servlet_CompletionStage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    AsyncTestBean at;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Fault Tolerance - @Asynchronous returning CompletionStage</h3>");
		
		try {
			String result = at.getAsyncStringCS().toCompletableFuture().get();
			out.println("Async CompletionStage Test : " + result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
		out.println("");
		
    }

}
