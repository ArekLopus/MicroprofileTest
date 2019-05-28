package metrics.metered;

import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.metrics.annotation.Metered;

@ApplicationScoped
public class MeteredTestBean {
	
	
	@Metered(name="MeteredTestConstructor")
	public MeteredTestBean() {}
	
	
	@Metered(name="MeteredTestMethod", absolute=true)
	public void meteredMethod() {
		
		int sleep = 850;
		
		try {
			TimeUnit.MILLISECONDS.sleep(1750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("From Metered Method (sleep time:" + sleep + ")");
	}

}
