package health;

//-Health checks are used to probe the state of a computing node from another machine (i.e. kubernetes service controller) with
// the primary target being cloud infrastructure environments where automated processes maintain the state of computing nodes.
//-In this scenario, health checks are used to determine if a computing node needs to be discarded (terminated, shutdown)
// and eventually replaced by another (healthy) instance.
//-It’s not intended (although could be used) as a monitoring solution for human operators.

//	Protocol
//-This project defines a protocol (wireformat, semantics and possible forms of interactions) between system components
// that need to determine the “liveliness” of computing nodes in a bigger architecture.
//-A detailed description of the health check protocol can be found in the companion document:
// https://github.com/eclipse/microprofile-health/blob/master/spec/src/main/asciidoc/protocol-wireformat.adoc

//	API Usage
//-The main API to provide health check procedures on the application level is the HealthCheck interface:
//	@FunctionalInterface
//	public interface HealthCheck {
//	    HealthCheckResponse call();
//	}

//	Constructing `HealthCheckResponse`s
//-Application level code is expected to use one of static methods on HealthCheckResponse
// to retrieve a HealthCheckResponseBuilder used to construct a response, i.e. :
//	public class SuccessfulCheck implements HealthCheck {
//	    @Override
//	    public HealthCheckResponse call() {
//	        return HealthCheckResponse.named("successful-check").up().build();
//	    }
//	}

//	Integration with CDI
//-Within CDI contexts, beans that implement HealthCheck and annotated with @Health are discovered automatically and are invoked
// by the framework or runtime when the outermost protocol entry point (i.e. http://HOST:PORT/health) receives an inbound request.
//	@Health
//	@ApplicationScoped
//	public class CheckDiskSpace implements HealthCheck {
//	    public HealthCheckResponse call() {  ...   }
//	}

//	Response
//-It’s the responsibility of the runtime to gather all HealthCheckResponse s for HealthCheck s known to the runtime. 
//-This means an inbound HTTP request will lead to a series of invocations on health check procedures and the runtime 
// will provide a composite response, with a single overall status, i.e.:
//	{
//	  "status": "UP",
//	  "checks": [ ... ]
//	}


//	REST interface specifications
//	Context		Verb	Status Code		Response
//	/health		GET		200, 500, 503	https://github.com/eclipse/microprofile-health/blob/master/spec/src/main/asciidoc/protocol-wireformat.adoc#appendix-b-json-payload-specification

//Status Codes:
// • 200 - for a health check with a positive status (UP)
// • 503 - in case the overall status is negative (DOWN)
// • 500 - in case the producer wasn’t able to process the health check request (i.e. error in procedure)



public class Info {}