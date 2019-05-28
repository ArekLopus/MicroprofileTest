package faulttolerance.asynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

import org.eclipse.microprofile.faulttolerance.Asynchronous;

public class AsyncTestBean {
	
	@Asynchronous
	public Future<String> getAsyncString() {
		try {
			Thread.sleep(500);
			System.out.println("getAsyncString(), thread: "+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture("getAsyncString(), thread: "+Thread.currentThread().getName());
	}
	
	
	//Needs MP 2.2 or Fault Tolerance 2.0
	//https://projects.eclipse.org/projects/technology.microprofile/releases/fault-tolerance-2.0
	//@Asynchronous
	public CompletionStage<String> getAsyncStringCS() {
		try {
			Thread.sleep(500);
			System.out.println("getAsyncStringCS(), thread: "+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture("getAsyncStringCS(), thread: "+Thread.currentThread().getName());
	}
}
