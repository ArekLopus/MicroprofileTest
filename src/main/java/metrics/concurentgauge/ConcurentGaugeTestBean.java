
package metrics.concurentgauge;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConcurentGaugeTestBean {
	
	private AtomicInteger counter = new AtomicInteger(2);
	
	//name=ConcurentGaugeTestMethod
	public int concurentGaugeMethod() {
		return counter.get();
	}
}
