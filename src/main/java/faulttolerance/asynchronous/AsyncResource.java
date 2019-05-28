package faulttolerance.asynchronous;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

// http://localhost:8080/MicroprofileTest/res/faulttolerance/async
// http://localhost:8080/MicroprofileTest/res/faulttolerance/asyncCS

@Path("faulttolerance")
public class AsyncResource {
	
	@Inject
	AsyncTestBean at;
	
	@Path("async")
	@GET
	public String testAsync() throws InterruptedException, ExecutionException {
		System.out.println("AsyncResource's testAsync() called");
		String asyncThread = at.getAsyncString().get();
		return "Thread of the endpoint: "+ Thread.currentThread().getName() + "<br/>" + asyncThread;
	}
	
	
	//Needs MP 2.2 or Fault Tolerance 2.0
	//https://projects.eclipse.org/projects/technology.microprofile/releases/fault-tolerance-2.0
	@Path("asyncCS")
	@GET
	public CompletionStage<Response> testAsyncCS() {
		
		return at.getAsyncStringCS()
				.thenApply( r -> Response.accepted(r).header("X-Operation", "Operation CompletionStage Successful").build() )
				.exceptionally(t -> Response.status(Response.Status.NOT_ACCEPTABLE).header("X-Exception", t.getMessage()).build());
		
	}
	
}
