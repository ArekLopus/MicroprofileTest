package configuration.console;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import configuration.customsources.CustomConfigSourceMy;
import configuration.customsources.EchoConfigSource;
import configuration.customsources.RandomIntConfigSource;

//Needs 'wildfly-microprofile-config-implementation' lib to run 

public class CustomConfigSourceTest {
	
	public CustomConfigSourceTest() {
		
		ConfigBuilder builder = ConfigProviderResolver.instance().getBuilder();
		builder.addDefaultSources();
		
		CustomConfigSourceMy ccs = new CustomConfigSourceMy();
		EchoConfigSource ecs = new EchoConfigSource();
		RandomIntConfigSource rcs = new RandomIntConfigSource(); 
		builder.withSources(ccs, ecs, rcs);
		
		//or instead above
		//builder.addDiscoveredSources();
		
		Config config = builder.build();
		
		String propName1 = config.getValue("random.int", String.class);
		String propName2 = config.getValue("echo.testing", String.class);
		String propName3 = config.getValue("three", String.class);
		
		System.out.println("RandomIntConfigSource:\t" + propName1);
		System.out.println("EchoConfigSource:\t" + propName2);
        System.out.println("CustomConfigSourceMy:\t" + propName3);
	}
	
    public static void main(String... args) {
        new CustomConfigSourceTest();
    }
    
    
    
    
    
}

