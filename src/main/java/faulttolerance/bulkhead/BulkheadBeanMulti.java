package faulttolerance.bulkhead;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.faulttolerance.Bulkhead;

@ApplicationScoped
public class BulkheadBeanMulti {
    
	private long SLEEP = 3000;
	
    @Bulkhead(3)
    public String testMmethodOne() {
    	try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ex) {
            System.out.println("Exception!: " + ex.getMessage());
        }
    	
    	//System.out.println("MmethodOne, thread: " + Thread.currentThread().getName());
    	return "MmethodOne, thread: " + Thread.currentThread().getName();
    }
    
    
    @Bulkhead(3)
    public String testMmethodTwo() {
    	try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ex) {
            System.out.println("Exception!: " + ex.getMessage());
        }
    	
    	System.out.println("MmethodTwo, thread: " + Thread.currentThread().getName());
    	return "MmethodTwo, thread: " + Thread.currentThread().getName();
    }
    
    

    
    public String testMmethod() {
        
        
        return "After Sleep Finished";
    }
}