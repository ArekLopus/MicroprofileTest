package opentracing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.opentracing.Traced;

//http://localhost:8080/MicroprofileTest/res/opentracing					// "operationName":"GET:opentracing.TracingTest.testTracingMethod
//http://localhost:8080/MicroprofileTest/res/opentracing/name				// "operationName":"traced-With-name"

@Traced
@Path("/opentracing")
@Produces(MediaType.TEXT_PLAIN)
public class TracingTest {
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response testTracingMethod() {
		System.out.println("Testing...");
		return Response.ok("Testing OpenTracing").build();
		
	}
	
	@Path("/name")
	@Traced(operationName="traced-With-name")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response testTracingMethodWithName() {
		System.out.println("Testing...");
		return Response.ok("Testing OpenTracing With Name").build();
		
	}
	
}
