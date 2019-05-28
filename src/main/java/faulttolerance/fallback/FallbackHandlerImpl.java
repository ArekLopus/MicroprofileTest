package faulttolerance.fallback;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

public class FallbackHandlerImpl implements FallbackHandler<String> {

	@Override
	public String handle(ExecutionContext context) {
		String info = "---- FALLBACK HANDLER (FallbackHandlerImpl.class) CALLED ----";
		return info;
	}
	
}