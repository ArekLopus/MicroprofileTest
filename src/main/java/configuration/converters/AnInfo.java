package configuration.converters;

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
//	publicclass StringPrefixConverter implements Converter<String> {
//		@Override
//		public String convert(String value) throws IllegalArgumentException {
//    		return"Converted:" + value;
//		}
//	}

//	Java ServiceLoader support for converters
//-The Java ServiceLoader pattern can also be used to locate custom converters if their package qualified class names appear
// in a service file of the form, ${CLASSPATH}/META-INF/services/org.eclipse.microprofile.config.spi.Converter.
//-Both default converters included in the MicroProfile Config API implementation, and converters that are discovered
// by using the Java ServiceLoader pattern are available for all configurations to use.


public class AnInfo {}