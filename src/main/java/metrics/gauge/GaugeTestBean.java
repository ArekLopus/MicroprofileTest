
package metrics.gauge;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;

@ApplicationScoped
public class GaugeTestBean {
	
	private AtomicInteger counter = new AtomicInteger(0);
	
	//@Gauge(unit = "counter", name="GaugeTestMethod")
	@Gauge(unit = MetricUnits.PERCENT, name="GaugeTestMethod")
	public int gaugeMethod() {
		return counter.getAndIncrement();
	}
	
	// Need to be run once before can be accessed. (In servlet it would create 2 increases)
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
		counter.getAndIncrement();
	}

}
