package configuration.console;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

//Needs 'wildfly-microprofile-config-implementation' lib to run 

//	ConfigProvider
//-This is the central class to access a Config. 

public class ConfigProviderTest {
	
	public ConfigProviderTest() {
		
        Config config = ConfigProvider.getConfig();

        Integer propName = config.getValue("random.int.property", Integer.class);
        //Integer propName = config.getValue("microprofile.config.refresh.rate", Integer.class);
        
        System.out.println("random.int.property:\t" + propName);
        
	}
	
    public static void main(String... args) {
        new ConfigProviderTest();
    }
}