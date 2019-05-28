package jaxrs;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ping")
@Produces(MediaType.TEXT_PLAIN)
public class PingTest {
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response testGet() {
		System.out.println("Testing...");
		return Response.ok(this.sysInfo()).build();
	}
	
	
	private String sysInfo() {
		
		Runtime runtime = Runtime.getRuntime();
		String currentDate = String.format("Server time: %tI:%<tM:%<tS%<tp, %<te-%<tm-%<tY %n", new Date());
		
		String info = currentDate +
				"OS: "+System.getProperty("os.name")+"\n"+
				"Java Runtime: "+System.getProperty("java.runtime.version")+"\n"+
				"VM Name: "+System.getProperty("java.vm.name")+"\n"+
				"VM Vendor: "+System.getProperty("java.vm.vendor")+"\n"+
				"Processors/Cores: "+runtime.availableProcessors()+"\n"+
				"Max Memory: "+runtime.maxMemory()/(1024*1024)+" MB\n" +
				"Total Memory: "+runtime.totalMemory()/(1024*1024)+" MB\n" +
				"Free Memory: "+runtime.freeMemory()/(1024*1024)+" MB\n";
		
		return info;
	}
	
	@Resource
	ManagedExecutorService mes;
	
	@Path("1")
	@GET
	public void timedReturnTest(@Suspended AsyncResponse ar) {
		
		CompletableFuture.supplyAsync(() -> {
			int sleep = 1;
			try {
				TimeUnit.SECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Test 1, I just slept " + sleep + " sec" + ", obj: "+ this +", thread: "+Thread.currentThread().getName();
		}, mes).thenAccept(ar::resume);
		
		//return "Hi, I just slept " + sleep + " secs";
	}
	@Path("2")
	@GET
	public void timedReturnTest2(@Suspended AsyncResponse ar) {
		
		CompletableFuture.supplyAsync(() -> {
			int sleep = 1;
			try {
				TimeUnit.SECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Test 2, I just slept " + sleep + " sec" + ", obj: "+ this +", thread: "+Thread.currentThread().getName();
		}, mes).thenAccept(ar::resume);
		
		//return "Hi, I just slept " + sleep + " secs";
	}
	
	@Context
	HttpServletResponse resp;
	@Context
	HttpServletRequest req;
	
	@Path("auth")
	@GET
	public Response authTest() {
		
		String header = req.getHeader("AUTHORIZATION");
		
		
		resp.addHeader("X-My", "Blah");
		resp.addHeader("AUTHORIZATION", "Bearer 324wefrwef");
		return Response.ok("AUTHORIZATION: "+header).build();
	}
}
