package restclient.async;

import org.eclipse.microprofile.rest.client.ext.AsyncInvocationInterceptor;
import org.eclipse.microprofile.rest.client.ext.AsyncInvocationInterceptorFactory;

public class RestClientAsyncInterceptorFactory implements AsyncInvocationInterceptorFactory {

    public AsyncInvocationInterceptor newInterceptor() {
         return new RestClientAsyncInterceptor();
    }
}