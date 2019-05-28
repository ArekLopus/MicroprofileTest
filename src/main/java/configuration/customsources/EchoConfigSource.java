package configuration.customsources;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.config.spi.ConfigSource;

public class EchoConfigSource implements ConfigSource {

    @Override
    public Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("echo.property", "generated dynamically");
        return properties;
    }

    @Override
    public int getOrdinal() {
        return 100;
    }

    @Override
    public String getValue(String propertyName) {
        if (propertyName.startsWith("echo.")) {
            return "Echoooo: " + propertyName;
        }

        return null;
    }

    @Override
    public String getName() {
        return "Echo";
    }

}