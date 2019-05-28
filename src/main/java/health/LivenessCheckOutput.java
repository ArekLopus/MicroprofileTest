package health;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

// Needs WildFly: MicroProfile Rest Client Impl to run.
public class LivenessCheckOutput {

	public LivenessCheckOutput() {
		
		URI uri = URI.create("http://localhost:8080/");
		
		LivenessCheckOutputIntf client = RestClientBuilder.newBuilder()
			.baseUri(uri)
			.build(LivenessCheckOutputIntf.class);

		String resp = client.checkHealth();
		
		StringReader sr = new StringReader(resp);
		StringWriter sw = new StringWriter();
		Map<String, Boolean> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);	// pretty printing feature is added.
		
		JsonStructure jo = Json.createObjectBuilder().build();
		
		try (JsonReader reader = Json.createReader(sr)) {
			    jo = reader.read();
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
		
		
		try (JsonWriter writer = Json.createWriterFactory(config).createWriter(sw)) {
		   writer.write(jo);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		System.out.println(sw.toString());
		
	}

	public static void main(String[] args) {
		new LivenessCheckOutput();
	}

}
