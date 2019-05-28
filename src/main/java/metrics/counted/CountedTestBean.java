
package metrics.counted;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.metrics.annotation.Counted;

@ApplicationScoped
public class CountedTestBean {
	
	@Counted(name="CountedTestConstructor", monotonic=true)
	public CountedTestBean() {}
	
	@Counted(name="CountedTestMethod", monotonic=true, absolute=true)
	public String countedMethod() {
		System.out.println("From Counted Method");
		return "From Counted Method "+System.currentTimeMillis();
	}
}
