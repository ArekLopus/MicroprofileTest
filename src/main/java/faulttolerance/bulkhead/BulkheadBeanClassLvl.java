package faulttolerance.bulkhead;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Fallback;

@Bulkhead(3)
@ApplicationScoped
public class BulkheadBeanClassLvl {
    
	private long SLEEP = 3000;
    
    public String testMmethod() {
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ex) {
            System.out.println("Exception!: " + ex.getMessage());
        }
        
        return "Returned After Sleep Finished";
    }
    
    
    @Fallback(fallbackMethod="failed")
    public String testMmethodFallback() {
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ex) {
            System.out.println("Exception!: " + ex.getMessage());
        }
        
        return "Returned After Sleep Finished";
    }
    
    public String failed() {
		//System.out.println("--- After Sleep Error,  Fallback Method Called, thread: " + Thread.currentThread().getName());
		return "--- After Sleep Error,  Fallback Method Called";
	}
}