package configuration.console;

// JavaSE needs 'wildfly-microprofile-config-implementation' lib to run
//-Manually creating custom Config or use it in JavaSE 

//	ConfigProvider
//-This is the central class to access a Config. 

//	ConfigBuilder
//-Builder for manually creating an instance of a Config.
//-Unlike the ConfigProvider interface, the ConfigBuilder interface initially has an empty set of configuration property sources.
// It is needed to add the default sources or / and custom sources
//	builder.addDefaultSources();
//-A user class that implements org.eclipse.microprofile.config.spi.ConfigSource can be registered with a ConfigBuilder.
//-In this way, this user class is then included in later in configurations that the builder produces.
//	MySource source = new MySource();
//	builder.withSources(source, source2);
//-or for all automatically discovered
//	builder.addDiscoveredSources()

public class AnInfo {}