package configuration.console;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import configuration.customsources.CustomConfigSourceMy;
import configuration.customsources.EchoConfigSource;
import configuration.customsources.RandomIntConfigSource;

//Needs 'wildfly-microprofile-config-implementation' lib to run 

public class CustomConfigSourceTestAll {
	
	public CustomConfigSourceTestAll() {
		
		ConfigBuilder builder = ConfigProviderResolver.instance().getBuilder();
		builder.addDefaultSources();
		
		CustomConfigSourceMy ccs = new CustomConfigSourceMy();
		EchoConfigSource ecs = new EchoConfigSource();
		RandomIntConfigSource rcs = new RandomIntConfigSource(); 
		builder.withSources(ccs, ecs, rcs);
		
		Config config = builder.build();
		
		config.getConfigSources().forEach(e -> System.out.println(e.getName()));
		
//		List<ConfigSource> list = new ArrayList<>();
//		config.getConfigSources().forEach(list::add);
//		System.out.println(list.get(5).getName());
//		System.out.println(list.get(5).getOrdinal());
//		System.out.println(list.get(5).getProperties());
//		System.out.println(list.get(5).getPropertyNames());
		
	}
	
    public static void main(String... args) {
        new CustomConfigSourceTestAll();
    }
    
    
    
    
    
}

