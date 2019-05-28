package metrics.timed;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.metrics.annotation.Timed;

@ApplicationScoped
public class TimedTestBean {
	
	
	@Timed(name="TimedTestConstructor")
	public TimedTestBean() {}
	
	
	@Timed(name="TimedTestMethod", absolute=true)
	public void timedMethod() {
		
		int sleep = 850;
		
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("From Timed Method (sleep time:" + sleep + ")");
	}

}
