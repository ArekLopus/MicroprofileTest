package configuration.customsources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.microprofile.config.spi.ConfigSource;

public class RandomIntConfigSource implements ConfigSource {
	
	private ThreadLocalRandom tlr = ThreadLocalRandom.current();
	
	@Override
	public Map<String, String> getProperties() {
		return new HashMap<>();
	}

    @Override
    public int getOrdinal() {
        return 100;
    }

    @Override
    public String getValue(String propertyName) {
        if (propertyName.startsWith("random.")) {
            return String.valueOf(tlr.nextInt(1000));
        }
        return null;
    }

    @Override
    public String getName() {
        return "Random Integer Generator";
    }

}
