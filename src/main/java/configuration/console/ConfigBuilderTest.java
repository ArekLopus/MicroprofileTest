package configuration.console;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

//Needs 'wildfly-microprofile-config-implementation' lib to run

//	ConfigBuilder
//-Builder for manually creating an instance of a Config.
//-By default the ConfigBuilder interface initially has an empty set of configuration property sources and converters.

//-For users that want to create configurations in a more customized way, a configuration builder API can be used to set various
// options before the configuration is generated.
//	ConfigBuilder builder = ConfigProviderResolver.instance().getBuilder();
//	builder.addDefaultSources();
//	Config config = builder.build();
//-builder.addDefaultSources() adds in the same set of default sources the ConfigProvider uses to build configurations.
//-Other configuration sources can also be added.
//	builder.withSources(source, source2);

//-addDefaultSources() - adds the default config sources appearing on the builder's classpathincluding:
// 1.System properties
// 2.Environment properties
// 3./META-INF/microprofile-config.properties

//	Methods
// addDefaultSources(), addDiscoveredConverters(), addDiscoveredSources(), build(), withConverter(), withSources(), forClassLoader()

public class ConfigBuilderTest {
	
	public ConfigBuilderTest() {
		
		ConfigBuilder builder = ConfigProviderResolver.instance().getBuilder();
		builder.addDefaultSources();
		builder.addDiscoveredSources();
		Config config = builder.build();

        String propName1 = config.getValue("one", String.class);
		Long propName2 = config.getValue("my.long.property", Long.class);
        
		System.out.println("CustomSource property -> 'one':\t\t\t\t\t" + propName1);
        System.out.println("META-INF/microprofile-config.properties -> my.long.property:\t" + propName2);
        System.out.println("Windows Environment Variable 'TEST':\t\t\t\t" + config.getOptionalValue("TEST", String.class).orElse("NOT AVAILABLE"));
	}
	
    public static void main(String... args) {
        new ConfigBuilderTest();
    }
}