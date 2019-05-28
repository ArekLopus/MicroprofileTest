package openapi;

//		Annotations
//-Examples: https://github.com/eclipse/microprofile-open-api/wiki
//-In general they produce more description of an endpoint.
//-Many of these OpenAPI v3 annotations were derived from the Swagger Core library, which allows for a mostly-mechanical
// transformation of applications that are using that library and wish to take advantage to the official MP OpenAPI interfaces.

//	Quick overview of annotations
//-The following annotations are found in the org.eclipse.microprofile.openapi.annotations package.

//Annotation					Description
//org.eclipse.microprofile.openapi.annotations.callbacks
//@Callback (M,T)				- Represents a callback URL that will be invoked.
//@Callbacks (M)				- Represents an array of Callback URLs that can be invoked.
//@CallbackOperation ()			- Represents an operation that will be invoked during the callback.
//
//org.eclipse.microprofile.openapi.annotations
//@Components ()				- A container that holds various reusable objects for different aspects of the OpenAPI Specification.
//@ExternalDocumentation (M,T)	- References an external resource for extended documentation.
//@OpenAPIDefinition (T,Pck)	- General metadata for an OpenAPI definition.
//@Operation (M)				- Describes an operation or typically a HTTP method against a specific path.
//
//org.eclipse.microprofile.openapi.annotations.extensions
//@Extension (F,M,P,T)			- Adds an extension with contained properties.
//@Extensions (F,M,P,T)			- Adds custom properties to an extension.
//
//org.eclipse.microprofile.openapi.annotations.headers
//@Header ()					- Describes a single header object.
//
//org.eclipse.microprofile.openapi.annotations.info
//@Contact ()					- Contact information for the exposed API.
//@Info ()						- This annotation encapsulates metadata about the API.
//@License ()					- License information for the exposed API.
//
//org.eclipse.microprofile.openapi.annotations.links
//@Link ()						- Represents a design-time link for a response.
//@LinkParameter ()				- Represents a parameter to pass to the linked operation.
//
//org.eclipse.microprofile.openapi.annotations.media
//@Content ()					- Provides schema and examples for a particular media type.
//@DiscriminatorMapping ()		- Used to differentiate between other schemas which may satisfy the payload description.
//@Encoding ()					- Single encoding definition to be applied to single Schema Object.
//@ExampleObject () 			- Illustrates an example of a particular content.
//@Schema (F,M,P,T)				- Allows the definition of input and output data types.
//
//org.eclipse.microprofile.openapi.annotations.parameters
//@Parameter (F,M,P)			- Describes a single operation parameter.
//@Parameters (M,P)				- Encapsulates input parameters.
//@RequestBody (M,P)			- Describes a single request body.
//
//org.eclipse.microprofile.openapi.annotations.responses
//@APIResponse (M)				- Describes a single response from an API operation.
//@APIResponses (M)				- A container for multiple responses from an API operation.
//
//org.eclipse.microprofile.openapi.annotations.security
//@OAuthFlow ()					- Configuration details for a supported OAuth Flow.
//@OauthFlows ()				- Allows configuration of the supported OAuth Flows.
//@OauthScope ()				- Represents an OAuth scope.
//@SecurityRequirement			- Specifies a security requirement for an operation.
//@SecurityRequirements (M,T)	- Represents an array of security requirements where only one needs to be satisfied.
//@SecurityRequirementsSet (M,T) - Represents an array of security requirements that need to be satisfied.
//@SecurityScheme (M,T)			- Defines a security scheme that can be used by the operations.
//@SecuritySchemes (T)			- Represents an array of security schemes that can be specified.
//
//org.eclipse.microprofile.openapi.annotations.servers
//@Server (M,T)					- Represents a server used in an operation or used by all operations in an OpenAPI document.
//@Servers (M,T)				- A container for multiple server definitions.
//@ServerVariable ()			- Represents a server variable for server URL template substitution.
//
//org.eclipse.microprofile.openapi.annotations.tags
//@Tag (M,T)					- Represents a tag for the API endpoint.
//@Tags (M,T)					- A container of multiple tags.


//Enums					Description
//org.eclipse.microprofile.openapi.annotations.enums
//Explode				- Enumeration used to define the value of the explode property.
//ParameterIn			- Enumeration representing the parameter’s in property.
//ParameterStyle		- Enumeration for the parameter’s style property.
//SchemaType 			- ?ARRAY, BOOLEAN, DEFAULT, INTEGER, NUMBER, OBJECT, STRING
//SecuritySchemeIn		- Enumeration for the security scheme’s in property.
//SecuritySchemeType	- Enumeration for the security scheme’s type property.


public class Info_Doc_Annotations {}