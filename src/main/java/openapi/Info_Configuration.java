package openapi;

//		Configuration
//-Configuration of various parts of this specification is provided via the MicroProfile Config mechanism.
//-There are various ways to inject these config values into an MP OpenAPI framework, including the default or custom ConfigSource.
//-Vendors implementing the MP OpenAPI specification can optionally provide additional native ways for these configuration values
// to be injected into the framework (e.g. via a server configuration file), as long as they also implement the MP Config specification.

//	List of configurable items
//-Vendors must support all the Core configurations of this specification. Optionally, they may also support Vendor extensions
// that allow the configuration of framework-specific values for configurations that affect implementation behavior.
//-For convenience of vendors (and application developers using custom ConfigSources),
// the full list of supported configuration keys is available as constants in the OASConfig class.

//	Core configurations
//-The following is a list of configuration values that every vendor must support.

//	Config key						Value description
//	mp.openapi.model.reader				- Configuration property to specify the fully qualified name of the OASModelReader impl.
//	mp.openapi.filter					- Configuration property to specify the fully qualified name of the OASFilter implementation.
//	mp.openapi.scan.disable				- Configuration property to disable annotation scanning. Default value is false.
//	mp.openapi.scan.packages			- Configuration property to specify the list of packages to scan. For example,
//										  mp.openapi.scan.packages=com.xyz.PackageA,com.xyz.PackageB
//	mp.openapi.scan.classes				- Configuration property to specify the list of classes to scan. For example,
//										  mp.openapi.scan.classes=com.xyz.MyClassA,com.xyz.MyClassB
//	mp.openapi.scan.exclude.packages	- Configuration property to specify the list of packages to exclude from scans. For example, 		  mp.openapi.scan.exclude.packages=com.xyz.PackageC,com.xyz.PackageD
//	mp.openapi.scan.exclude.classes		- Configuration property to specify the list of classes to exclude from scans. For example, 		  mp.openapi.scan.exclude.classes=com.xyz.MyClassC,com.xyz.MyClassD
//	mp.openapi.servers					- Configuration property to specify the list of global servers that provide connectivity info. Fe,
//										  mp.openapi.servers=https://xyz.com/v1,https://abc.com/v1
//	mp.openapi.servers.path.			- Prefix of the configuration property to specify an alternative list of servers to service all operations 
//										  in a path. For example, mp.openapi.servers.path./airlines/bookings/{id}=https://xyz.io/v1
//	mp.openapi.servers.operation.		- Prefix of the configuration property to specify an alternative list of servers to service an operation.
//										  Operations that want to specify an alternative list of servers must define an operationId, a unique
//										  string used to identify the operation. Fe, mp.openapi.servers.operation.getBooking=https://abc.io/v1
//Vendor extensions
//	mp.openapi.extensions	- Vendors that wish to provide vendor-specific configuration via MP Config (instead of another
//							  native configuration framework) must use the prefix.



public class Info_Configuration {}