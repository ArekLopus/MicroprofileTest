package opentracing;

//		OpenTracing
//-This spec specifically does not address the problem of defining, implementing, or configuring the underlying distributed tracing system. 
//-The proposal assumes an environment where all services use a common OpenTracing impl (all Zipkin, Jaeger, ... compatible).

//-Work on defining standard wire protocols and consistent APIs for handling trace (and metric) data is being done at OpenCensus.
// The OpenCensus API appears very similar to OpenTracing, but support for OpenCensus Tracers will require a separate MicroProfile spec.

//-Distributed tracing allows you to trace the flow of a request across service boundaries.
//-This is particularly important in a microservices environment where a request typically flows through multiple services.
//-To accomplish distributed tracing, each service must be instrumented to log messages with a correlation id that may have been propagated from an upstream service. 
//-A common companion to distributed trace logging is a service where the distributed trace records can be stored. 
//-The storage service for distributed trace records can provide features to view the cross service trace records associated with particular request flows.
//-See also examples on opentracing.io.
//-This specification defines an API and MicroProfile behaviors that allow services to easily participate in an environment where distributed tracing is enabled.
//-This specification specifically addresses the problem of making it easy to instrument services with distributed tracing function,
// given an existing distributed tracing system in the environment.
//-The information about a Span that is propagated between services is typically called a SpanContext.
//-It is not the intent of this specification to define the exact format for how SpanContext information is stored or propagated.
//-Our use case is for applications running in an environment where all applications use the same Tracer implementation,
// and microservices that require explicit tracing logic use the OpenTracing API. 

//	Rationale
//-In order for a distributed tracing system to be effective and usable, two things are required
//  1. The different services in the environment must agree on the mechanism for transferring correlation ids across services.
//  2. The different services in the environment should produce their trace records in format that is consumable by the storage service
//      for distributed trace records.

//-Without the first, some services will not be included in the trace records associated with a request.
//-Without the second, custom code would need to be written to present the information about a full request flow.

//-There are existing distributed tracing systems that provide a server for distributed trace record storage and viewing, and application
// libraries for instrumenting microservices. The problem is that the different distributed tracing systems use implementation specific
// mechanisms for propagating correlation IDs and for formatting trace records, so once a microservice chooses a distributed tracing 
// implementation library to use for its instrumentation, all other microservices in the environment are locked into the same choice.

//-The OpenTracing project’s purpose is to provide a standard API for instrumenting microservices for distributed tracing. If every
// microservice is instrumented for distributed tracing using the OpenTracing API, then (as long as an impl lib exists for the microservice’s
// language), the microservice can be configured at deploy time to use a common system impl to perform the log record formatting and cross
// service correlation id propagation. The common impl ensures that correlation ids are propagated in a way that is understandable
// to all services, and log records are formatted in a way that is understandable to the server for distributed trace record storage.

//-In order to make MicroProfile distributed tracing friendly, it will be useful to allow distributed tracing to be enabled on any
// MicroProfile application, without having to explicitly add distributed tracing code to the application.

//-In order to make MicroProfile as flexible as possible for adding distributed trace log records, MicroProfile should expose whatever
// objects are necessary for an application to use the OpenTracing API.


//	Architecture
//-This specification defines an easy way to allow an application running in a MicroProfile container to take advantage
// of distributed tracing by using an OpenTracing Tracer implementation. 
//-This document and impls MUST comply with OpenTracing specification and semantic conventions if it is not defined otherwise.

//-There are two operation modes
// • Without instrumentation of application code
// • With explicit code instrumentation


public class Info_ {}