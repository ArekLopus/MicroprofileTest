package configuration.customsources;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.config.spi.ConfigSource;

public class CustomConfigSourceMy implements ConfigSource {

	private Map<String, String> props = new HashMap<>();
	
	public CustomConfigSourceMy() {
		init();
	}
	
    @Override
    public int getOrdinal() {
        //return 120;
        return 520;
    }

    @Override
    public Map<String, String> getProperties() {
    	return props;
    }

    @Override
    public String getValue(String key) {
        return props.get(key);
    }

    @Override
    public String getName() {
        return "MyCustomDbConfig";
    }
    
    private void init() {
    	props.put("one", "OneValue");
        props.put("two", "TwoValue");
        props.put("three", "ThreeValue");
	}
    
}