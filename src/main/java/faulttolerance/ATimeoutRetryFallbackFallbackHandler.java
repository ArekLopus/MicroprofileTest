package faulttolerance;

import java.util.Arrays;
import java.util.List;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

public class ATimeoutRetryFallbackFallbackHandler implements FallbackHandler<List<String>> {
	
	@Override
    public List<String> handle(final ExecutionContext context) {
        System.out.println("--- FallbackHandler");
        return Arrays.asList("--- FallbackHandler");
    }
}