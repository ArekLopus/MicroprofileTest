package configuration.console;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

//Needs 'wildfly-microprofile-config-implementation' lib to run 
//Windows10 System -> AdvancedSystemSettings -> Environment Variables -> TEST - TEST_VALUE
public class ConfigWindowsEnvironmentVariables {
	
	public ConfigWindowsEnvironmentVariables() {
		
		//Get access to the Config instance
        Config config = ConfigProvider.getConfig();

        String propName1 = config.getValue("OS", String.class);
        String propName2 = config.getValue("NUMBER_OF_PROCESSORS", String.class);
        String propName3 = config.getValue("PROCESSOR_IDENTIFIER", String.class);
        String propName4 = config.getOptionalValue("TEST", String.class).orElse("NOT AVAILABLE");

        System.out.println("OS:\t\t\t" + propName1);
        System.out.println("NUMBER_OF_PROCESSORS:\t" + propName2);
        System.out.println("PROCESSOR_IDENTIFIER:\t" + propName3);
        System.out.println("TEST:\t\t\t" + propName4);
	}
	
    public static void main(String... args) {
        new ConfigWindowsEnvironmentVariables();
    }
}