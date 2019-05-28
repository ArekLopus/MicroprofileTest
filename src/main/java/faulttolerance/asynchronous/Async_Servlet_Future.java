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

//http://localhost:8080/MicroprofileTest/faulttoleranceAsynchronous
@WebServlet("/faulttoleranceAsynchronous")
public class Async_Servlet_Future extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    AsyncTestBean at;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Fault Tolerance - @Asynchronous</h3>");
		
		
		
		try {
			
			out.println("Async Test: " + at.getAsyncString().get());
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		out.println("");
		
    }

}
