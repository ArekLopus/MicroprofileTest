package openapi;

//		OpenAPI		http://localhost:8080/openapi
//-Exposing APIs has become an essential part of all modern apps. At the center of this revolution known as the API Economy we find RESTful APIs,
// which can transform any app into language agnostic services that can be called from anywhere: on-premises, private cloud, public cloud, etc.

//-For the clients and providers of these services to connect there needs to be a clear and complete contract.
//-Similar to the WSDL contract for legacy Web Services, the OpenAPI v3 specification is the contract for RESTful Services.

//-This MicroProfile spec, called OpenAPI 1.0, aims to provide a set of Java interfaces and programming models which allow
// Java developers to natively produce OpenAPI v3 documents from their JAX-RS applications.


//		Documentation Mechanisms
//-The MP OpenAPI spec requires vendors to produce a valid OpenAPI document from pure JAX-RS 2.0 applications. 
//-This means that vendors must process all the relevant JAX-RS annotations (such as @Path and @Consumes) as well as Java objects (POJOs)
// used as input or output to JAX-RS operations. This is a good place to start for application developers that are new to OpenAPI:
// just deploy your existing JAX-RS application into a MP OpenAPI vendor and check out the output from /openapi!

//-The application developer then has a few choices:
// • Augment those JAX-RS annotations with the OpenAPI Annotations. Using annotations means developers don’t have to re-write the portions
//   of the OpenAPI document that are already covered by the JAX-RS framework (e.g. the HTTP method of an operation).
// • Take the initial output from /openapi as a starting point to document your APIs via Static OpenAPI files. It’s worth mentioning that
//   these static files can also be written before any code. It is an approach often adopted by enterprises that want to lock-in the contract
//   of the API. In this case, we refer to the OpenAPI document as the "source of truth", by which the client and provider must abide.
// • Use the Programming model to provide a bootstrap (or complete) OpenAPI model tree.

//-Additionally, a Filter can update the OpenAPI model after it has been built from the previously described documentation mechanisms.

//	Annotations (Info_Doc_Annotations)
//-Examples: https://github.com/eclipse/microprofile-open-api/wiki
//-In general they produce more description of an endpoint.
//-Many of these OpenAPI v3 annotations were derived from the Swagger Core library, which allows for a mostly-mechanical transformation
// of applications that are using that library and wish to take advantage to the official MP OpenAPI interfaces.

//Overrides
//-When the same annotation is used on a class and a method, the values from the method instance will take precedence for that particular
// method. This commonly occurs with the @Server and @Tag annotations.
//-In other cases, such as with @Parameter and @RequestBody, the annotation values from the method’s parameters takes precedence over
// corresponding annotation values from the method itself - in this scenario the combined usage of these annotations is allowed
// but discouraged, as it is error prone.
//-The @Schema annotation has a complex set of possible combinations. It can placed on POJOs (and their fields / methods) and referenced
// from many other annotations. In the event that a @Schema#implementation value points to a POJO that also contains a @Schema annotation,
// the values are merged but with precedence given to the referrer annotation (i.e. the one that contains the implementation key).
// This allows POJO models to be reusable and configurable.

//	Static OpenAPI files
//-Application developers may wish to include a pre-generated OpenAPI document that was written separately from the code.

//-Depending on the scenario, the document may be fully complete or partially complete.
//-If a document is fully complete then the application developer will want to set the mp.openapi.scan.disable config property to true.
//-If a document is partially complete, then the application developer will need to augment the OpenAPI snippet with
// annotations, programming model, or via the filter.

//Location and formats
//-Vendors are required to fetch a single document named 'openapi' with an extension of yml, yaml or json,
// inside the application module’s root META-INF folder.
//-If there is more than one document found that matches one of these extensions the behavior of which file is chosen is undefined
// (i.e. each vendor may implement their own logic), which means that app devs should only place a single openapi document into that folder.
//-For convenience, you may also place your microprofile-config.properties in the root META-INF folder, if you wish to keep both documents
// in the same directory. This is in addition to the default locations defined by MicroProfile Config.

//	Programming model
//-Application developers are able to provide OpenAPI elements via Java POJOs.
//-The complete set of models are found in the org.eclipse.microprofile.openapi.models package.

//OASFactory
//-The OASFactory is used to create all of the elements of an OpenAPI tree.
//-For example, the following snippet creates a simple Info element that contains a title, description, and version.
//	OASFactory.createObject(Info.class).title("Airlines").description("Airlines APIs").version("1.0.0");

//OASModelReader
//-The OASModelReader interface allows app developers to bootstrap the OpenAPI model tree used by the processing framework.
//-To use it, simply create an implementation of this interface and register it using the mp.openapi.model.reader configuration key,
// where the value is the fully qualified name of the reader class.
//-Sample META-INF/microprofile-config.properties
//	mp.openapi.model.reader=com.mypackage.MyModelReader
//-Similar to static files, the model reader can be used to provide either complete or partial model trees. 
//-If providing a complete OpenAPI model tree, application developers should set the mp.openapi.scan.disable configuration to true.
//-Oherwise this partial model will be used as the base model during the processing of the other Documentation Mechanisms.
//-Vendors are required to call the OASReader a single time, in the order defined by the Processing rules section.
//-Only a single OASReader instance is allowed per application.

//	Filter
//-There are many scenarios where app developers may wish to update or remove certain elements and fields of the OpenAPI document.
//-This is done via a filter, which is called once after all other documentation mechanisms have completed.

//OASFilter
//-The OASFilter interface allows application developers to receive callbacks for various key OpenAPI elements.
//-The interface has a default implementation for every method, which allows app developers to only override the methods they care about. 
//-To use it, simply create an implementation of this interface and register it using the mp.openapi.filter configuration key,
// where the value is the fully qualified name of the filter class.
//-Sample META-INF/microprofile-config.properties
//	mp.openapi.filter=com.mypackage.MyFilter
//-Vendors are required to call the registered filter once for each filtered element. For example, the filterPathItem() is called for each
// corresponding PathItem element in the model tree. This allows application developers to filter the element and any of its descendants.
//-The order of filter methods called is undefined, with two exceptions:
// • All filterable descendant elements of a filtered element must be called before its ancestor.
// • The filterOpenAPI method must be the last method called on a filter (which is just a specialization of the first exception).


//	Processing rules
//-The processed document available from the OpenAPI Endpoint is built from a variety of sources,
// which were outlined in the sub-headings of Documentation Mechanisms. 

//-Vendors are required to process these different sources in the following order:
// 1 .Fetch configuration values from mp.openapi namespace
// 2. Call OASModelReader
// 3. Fetch static OpenAPI file
// 4. Process annotations
// 5. Filter model via OASFilter

//-Example processing:
// • A vendor starts by fetching all available Configuration. If an OASModelReader was specified in that configuration list,
//   its buildModel method is called to form the starting OpenAPI model tree for this application.
// • Any Vendor extensions are added on top of that starting model (overriding conflicts), or create a new model
//   if an OASModelReader was not registered.
// • The vendor searches for a file as defined in the section Static OpenAPI files. If found, it will read that document and merge with the
//   model produced by previous processing steps (if any), where conflicting elements from the static file will override the values from the original model.
// • If annotation scanning was not disabled, the JAX-RS and OpenAPI annotations from the application will be processed, further overriding any conflicting elements from the current model.
// • The final model is filtered by walking the model tree and invoking all registered OASFilter classes.


//		OpenAPI Endpoint
//Overview
//-A fully processed OpenAPI document must be available at the root URL /openapi, as a HTTP GET operation.
//	For example, GET http://myHost:myPort/openapi.
//-This document represents the result of the applied Processing rules.
//-The protocol required is http. Vendors are encouraged, but not required, to support the https protocol as well,
// to enable a secure connection to the OpenAPI endpoint.

//Content format
//-The default format of the /openapi endpoint is YAML.
//-Vendors must also support the JSON format if the request contains an Accept header with a value of application/json,
// in which case the response must contain a Content-Type header with a value of application/json.

//Query parameters
//-No query parameters are required for the /openapi endpoint. 
//-However, one suggested but optional query parameter for vendors to support is format, where the value can be either JSON or YAML,
// to facilitate the toggle between the default YAML format and JSON format.

//Context root behavior
//-Vendors are required to ensure that the combination of each global server element and pathItem element resolve to the absolute backend URL
// of that particular path. If that pathItem contains a servers element , then this list of operation-level server elements replaces
// the global list of servers for that particular pathItem.
//-For example: an application may have an ApplicationPath annotation with the value of /, but is assigned the context root of /myApp during
// deployment. In this case, the server elements (either global or operation-level) must either end with /myApp or a corresponding proxy.
// Alternatively it is valid, but discouraged, to add that context root (/myApp) to every pathItem defined in that application.

//Multiple applications
//-The 1.0 version of the MicroProfile OpenAPI specification does not define how the /openapi endpoint may be partitioned in the event that
// the MicroProfile runtime supports deployment of multiple applications.
//-If an implementation wishes to support multiple applications within a MicroProfile runtime, the semantics of the /openapi endpoint
// are expected to be the logical union of all the applications in the runtime, which would imply merging multiple OpenAPI documents
// into a single valid document (handling conflicting IDs and unique names).


//		Integration with other MicroProfile specifications
//-This section will outline specific integrations between MicroProfile OpenAPI and other MicroProfile specifications.
//
//MicroProfile Rest Client
//-It is common that a microservice (A) using MicroProfile OpenAPI will also use MicroProfile Rest Client to make outbound calls into
// microservice (B). In this case, we do not want the interface for microservice (B) to appear in microservice (A)'s OAS3 document.
//-Therefore, vendors are required to exclude from the final OAS3 document any interface annotated with 
// org.eclipse.microprofile.rest.client.inject.RegisterRestClient.


//	Limitations
//Internationalization
//-The 1.0 version of the MicroProfile OpenAPI spec does not require vendors to support multiple languages based on the Accept-Language.
// One reasonable approach is for vendors to support unique keys (instead of hardcoded text) via the various Documentation Mechanisms,
// so that the implementing framework can perform a global replacement of the keys with the language-specific text that matches
// the Accept-Language request for the /openapi endpoint. A cache of processed languages can be kept to improve performance.
//
//Validation
//-The MP OpenAPI 1.0 spec does not mandate vendors to validate the resulting OpenAPI v3 model (after processing the 5 steps previously mentioned),
// which means that the behavior of invalid models is vendor specific (i.e. vendors may choose to ignore, reject, or pass-through invalid inputs).
//
//Cross Origin Resource Sharing (CORS)
//-The MP OpenAPI 1.0 specification does not require vendors to support CORS for the /openapi endpoint.
//-The behavior of CORS requests is implementation dependent.

public class Info_Basics {}