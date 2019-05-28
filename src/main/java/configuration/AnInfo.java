package configuration;

//	Configuration for MicroProfile
//	https://github.com/eclipse/microprofile-config#configuration-for-microprofile
//-The MicroProfile Config API can be used by apps as a single API that can retrieve configuration info from different sources.
//-Config interface is used to get property value manually (It can be injected or created from ConfigProvider or ConfigBuilder).
//-@ConfigProperty is used to inject value, the same value that would be retrieved from an injected Config instance

//-When you use the MicroProfile Config API:
// • Multiple configuration sources can be amalgamated into a single configuration and accessed with one API.
// • Configuration property values can be overridden with values from config sources that are designated as having a higher precedence.
// • Values can be stored in named properties files, system environment variables, or Java™ System properties.
// • ConfigSource resources are loaded by using a Java ClassLoader, either the app’s current context ClassLoader or a users ClassLoader.
// • Values can be provided by registering a user implementation of a ConfigSource interface.
// • Values can be retrieved as Strings or as typed objects of a particular Java class by using built in or custom type Converters.
// • ConfigSource and Converter implementations can be discovered by using the Java ServiceLoader pattern.
// • Configuration property values, whether for primitives, standard types, or user supplied types, can be directly injected by using CDI.

//-The majority of applications need to be configured based on a running environment.
//-It must be possible to modify configuration data from outside an application so that the app itself does not need to be repackaged.

//-The configuration data can come from different locations and in different formats
// (e.g. system properties, system environment variables, .properties, .xml, datasource).
//-We call these config locations ConfigSources. If the same property is defined in multiple ConfigSources,
// we apply a policy to specify which one of the values will effectively be used.

//-Under some circumstances, some data sources may change dynamically. The changed values should be fed into the client without
// the need for restarting the app. This requirement is particularly important for microservices running in a cloud environment.
//-The MicroProfile Config approach allows to pick up configured values immediately after they got changed.


//	Design
//-The current configuration of an application can be accessed via ConfigProvider.getConfig().
//-A Config consists of the information collected from the registered org.eclipse.microprofile.config.spi.ConfigSource s.
//-These ConfigSources get sorted according to their ordinal. It is possible to overwrite configuration with lower importance from outside.

//-By default there are 3 default ConfigSources:
// • System.getProperties() (ordinal=400)
// • System.getenv() (ordinal=300)
// • all META-INF/microprofile-config.properties files on the ClassPath.
//   (default ordinal=100, separately configurable via a config_ordinal property inside each file)

//-The default values can be specified in the above files packaged with the app and the value can be overwritten later for each deployment.
//-A higher ordinal number takes precedence over a lower number.


//	Custom ConfigSources
//-It is possible to write and register a custom ConfigSources.
// An example would be a ConfigSource which gets the configured values from a shared database table in a cluster


//		Config Interface
//-Resolves the property value by searching through all configured ConfigSources.
//-If the same property is specified in multiple ConfigSources, the value in the ConfigSource with the highestordinal will be used. 
//-If multiple ConfigSources are specified withthe same ordinal, the ConfigSource.getName() will be used for sorting. 

//-For accessing the config you can use the ConfigProvider:  public void doSomething(
//	Config cfg = ConfigProvider.getConfig();
//	String archiveUrl = cfg.getValue("my.project.archive.endpoint", String.class);
//	Integer archivePort = cfg.getOptionalValue("my.project.archive.port", Integer.class).get();
 
//-It is also possible to inject the Config if a DI container is available: 
//	@Inject
//	private Config config;

//	ConfigBuilder
//-Builder for manually creating an instance of a Config.
//-For users that want to create configurations in a more customized way, a configuration builder API can be used to set various
// options before the configuration is generated.
//	ConfigBuilder builder = ConfigProviderResolver.instance().getBuilder();
//	builder.addDefaultSources();
//	Config config = builder.build();
//-builder.addDefaultSources() adds in the same set of default sources the ConfigProvider uses to build configurations.
//-Other configuration sources can also be added.
//	builder.withSources(source, source2);


//		@ConfigProperty
//-Binds the injection point with a configured value.
//-Injected values are the same values that would be retrieved from an injected Config instance
// or from the instance retrieved from ConfigProvider.getConfig()
//-Can be used to annotate injection points of type TYPE, Optional<TYPE> or javax.inject.Provider<TYPE>,
// where TYPE can be String and all types which have appropriate converters.
//-If @ConfigProperty is used with a type where no Converter exists, a deployment error will be thrown.
//-@ConfigProperty accepts a defaultValue param that will be used to set the field if the property is not present in the configuration.

//	Injecting Native Values
//-The first sample injects the configured value of the my.long.property property. 
//-The injected value does not change even if the underline property value changes in the Config.
//-Injecting a native value is recommended for a mandatory property and its value does not change at runtime or used by a bean with RequestScoped.
//-A further recommendation is to use the built in META-INF/microprofile-config.properties file mechanism to provide default values inside an Application. If no configured value exists for this property, a DeplymentException will be thrown during startup.
// @Inject
// @ConfigProperty(name="my.long.property")
// private Long injectedLongValue;

//	Injecting Optional Values
//-Contrary to natively injecting, if the property is not specified, this will not lead to a DeploymentException. 
//-The code injects a Long value to the my.optional.long.property. If the property does not exist, the value 123 will be assigned.
// @Inject
// @ConfigProperty(name="my.optional.long.property", defaultValue="123")
// private Long injectedLongValue;

//	Injecting Dynamic Values
//-The next sample injects a Provider for the value of my.long.property property to resolve the property dynamically.
//-Each invocation to Provider#get() will resolve the latest value from underlying Config again.
//-The existence of configured values will get checked during startup. Instances of Provider<T> are guaranteed to be Serializable.
// @Inject
// @ConfigProperty(name = "my.long.property" defaultValue="123")
// private Provider<Long> longConfigValue;


//		Configuration Sources
//-The configuration data can come from different locations and in different formats
// (e.g. system properties, system environment variables, .properties, .xml, datasource).

//	Default sources
//-By default there are 3 default ConfigSources:
// • System.getProperties() (ordinal=400)
// • System.getenv() (ordinal=300)
// • all META-INF/microprofile-config.properties files on the ClassPath.
//   (default ordinal=100, separately configurable via a config_ordinal property inside each file)

//-Unlike the ConfigProvider interface, the ConfigBuilder interface initially has an empty set of configuration property sources.
// It is needed to add the default sources or / and custom sources
//	builder.addDefaultSources();

//	User-provided ConfigSources
//-A user class that implements org.eclipse.microprofile.config.spi.ConfigSource can be registered with a ConfigBuilder.
//-In this way, this user class is then included in later in configurations that the builder produces.
//	MySource source = new MySource();
//	builder.withSources(source, source2);

//	Java ServiceLoader loading of ConfigSources
//-The Java ServiceLoader pattern can also be used to locate custom configuration source objects.
// A user class that implements the ConfigSource interface is loaded if its full package qualified class name is listed
// in a file of the form, ${CLASSPATH}/META-INF/services/org.eclipse.microprofile.config.spi.ConfigSource.


//		Converters
//-The MicroProfile Config API can also retrieve properties as Java object types with a genericized method
// that takes the type of the property object that is wanted.
//-This method can work for any type that has either a built-in or user supplied converter.

//	Built-in converters
//-The MicroProfile Config API includes built in converters for the following types: boolean, Boolean, int, Integer, long, Long,
// float, Float, double, Double, Duration, LocalTime, LocalDate, LocalDateTime, OffsetDateTime, OffsetTime, Instant, and URL.
//-Variables of any of these types can be directly injected, or retrieved by using the genericized getValue call,
// If the string value of the property can be successfully converted to the type by using the valueof, parse,
// or constructor method by taking a single String parameter.
//-Array Converters - For the built-in converters and custom converters, the corresponding Array converters are provided by default.
// The delimiter for the config value is ",". The escape character is "\".

//	Custom converters
//-Custom converters that implement the org.eclipse.microprofile.config.spi.Converter<T> interface
// can be registered and used in configurations by using the ConfigBuilder API.
//	ConfigBuilder builder = ConfigProviderResolver.instance().getBuilder();
//	builder.addDefaultSources();
//	Converter<CustomProperty> converter = new MyConverter(); 
//	builder.withConverters(converter);
//	Config config = builder.build();
//	Optional<CustomProperty> opt = config.getOptionalValue(“PROPOBJ”, CustomProperty.class);
//-withConverters() uses reflection to determine what type the converter is for.
//-Java Lambda code currently does not offer specific enough type info to the reflection APIs,
// so code that explicitly implements a Converter<T> interface is required for a custom converter.

//	Converter priority
//-If multiple converters for the same type exist, the converter that is used can be controlled by using a @Priority.
//-The default priority for a Converter if the @Priority annotation is not used is 100.
//-This method allows for Converter implementation to be overridden later in the application lifecycle.
//-A converter overrides any other converter with a lesser priority that is for the same type.
//	@Priority(200)
//	public class StringPrefixConverter implements Converter<String> {
//		@Override
//		public String convert(String value) throws IllegalArgumentException {
//   		return"Converted:" + value;
//		}
//	}

//	Java ServiceLoader support for converters
//-The Java ServiceLoader pattern can also be used to locate custom converters if their package qualified class names appear
// in a service file of the form, ${CLASSPATH}/META-INF/services/org.eclipse.microprofile.config.spi.Converter.
//-Both default converters included in the MicroProfile Config API implementation, and converters that are discovered
// by using the Java ServiceLoader pattern are available for all configurations to use.



public class AnInfo {}
