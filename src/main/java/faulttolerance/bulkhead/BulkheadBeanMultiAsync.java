package faulttolerance.bulkhead;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;

@ApplicationScoped
public class BulkheadBeanMultiAsync {
    
	private long SLEEP = 3000;
	
	@Asynchronous
	@Bulkhead(value = 2, waitingTaskQueue = 2)
    public Future<String> testMmethodOne() {
    	try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ex) {
            System.out.println("Exception!: " + ex.getMessage());
        }
    	
    	//System.out.println("MmethodOne, thread: " + Thread.currentThread().getName());
    	return CompletableFuture.completedFuture("MmethodOne, thread: " + Thread.currentThread().getName());
    }
    
    
	@Asynchronous
	@Bulkhead(value = 2, waitingTaskQueue = 2)
    public Future<String> testMmethodTwo() {
    	try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ex) {
            System.out.println("Exception!: " + ex.getMessage());
        }
    	
    	//System.out.println("MmethodTwo, thread: " + Thread.currentThread().getName());
    	return CompletableFuture.completedFuture("MmethodTwo, thread: " + Thread.currentThread().getName());
    }
    
    

    
    public String testMmethod() {
        
        
        return "After Sleep Finished";
    }
}