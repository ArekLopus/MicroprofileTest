package restclient.async;

//		MicroProfile Rest Client Asynchronous Support
//-It is possible for Rest Client interface methods to be declared asynchronous. 
//-This allows the thread invoking the interface method to proceed while the RESTful request occurs on another thread.

//	Asynchronous Methods
//-A method is considered to be asynchronous if the method’s return type is java.util.concurrent.CompletionStage.
//-For example, the following methods would be declared asynchronous:
//	public interface MyAsyncClient {
//	    @GET
//	    @Path("/one")
//	    CompletionStage<Response> getCompletionStage();
//
//	    @POST
//	    @Path("/two")
//	    CompletionStage<String> post(String entity);
//	}

//	ExecutorService
//-By default, the MicroProfile Rest Client implementation can determine how to implement the asynchronous request.
//-The primary requirement for the implementation is that the response from the remote server should be
// handled on a different thread than the thread invoking the asynchronous method.
//-Providers on the outbound client request chain (such as `ClientRequestFilter`s, `MessageBodyWriter`s, `WriterInterceptor`s, etc.)
// may be executed on either thread.

//-Callers may override the default impl by providing ExecutorService via the RestClientBuilder.executorService(ExecutorService).
//-The impl must use the ExecutorService provided for all asynchronous methods on any interface built via the RestClientBuilder.

//	AsyncInvocationInterceptors
//-There may be cases where it is necessary for client application code or runtime components to be notified
// when control of the client request/response has flowed from one thread to another. 
//-This can be accomplished by registering an implementation of the AsyncInvocationInterceptorFactory provider interface. 
//-MP Rest Client implementations must invoke the newInterceptor() of each registered factory
// provider prior to switching thread of execution on async method requests.
// That method will return an instance of AsyncInvocationInterceptor - the MP Rest Client impl must then invoke
// the prepareContext() while still executing on the thread that invoked the async method.
// After swapping threads, but before invoking further providers or returning control back to the async method caller,
// the MP Rest Client implementation must invoke the applyContext() on the new async thread that will complete the request/response.

//-The following example shows how the AsyncInvocationInterceptorFactory provider and associated AsyncInvocationInterceptor interface could be used to propagate a ThreadLocal value from the originating thread to async thread:
//	public class MyFactory implements AsyncInvocationInterceptorFactory {
//	    public AsyncInvocationInterceptor newInterceptor() {
//	         return new MyInterceptor();
//	    }
//	}
//
//	public class MyInterceptor implements AsyncInvocationInterceptor {
//	    // This field is temp storage to facilitate copying a ThreadLocal value from the originating thread to the new async thread.
//	    private volatile String someValue;
//
//	    public void prepareContext() {
//	        someValue = SomeClass.getValueFromThreadLocal();
//	    }
//	    public void applyContext() {
//	        SomeClass.setValueIntoThreadLocal(someValue);
//	    }
//	}
//
//	@RegisterProvider(MyFactory.class)
//	public interface MyAsyncClient {...}


public class AnInfo {}