package restclient.async;

import org.eclipse.microprofile.rest.client.ext.AsyncInvocationInterceptor;

public class RestClientAsyncInterceptor implements AsyncInvocationInterceptor {

    public void prepareContext() {
        System.out.println("AsyncInvocationInterceptor - prepareContext(), thread: " + Thread.currentThread().getName());
    }
    public void applyContext() {
    	System.out.println("AsyncInvocationInterceptor - applyContext()), thread: " + Thread.currentThread().getName());
    }
}