package configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

//		Configuration Sources
//-The configuration data can come from different locations and in different formats
// (e.g. system properties, system environment variables, .properties, .xml, datasource).

//	Default sources
//-By default there are 3 default ConfigSources:
// • System.getProperties() (ordinal=400)
// • System.getenv() (ordinal=300)
// • all META-INF/microprofile-config.properties files on the ClassPath.
//   (default ordinal=100, separately configurable via a config_ordinal property inside each file)

//-Unlike the ConfigProvider interface, the ConfigBuilder interface initially has an empty set of configuration property sources.
// It is needed to add the default sources or / and custom sources
//	builder.addDefaultSources();

//	User-provided ConfigSources
//-A user class that implements org.eclipse.microprofile.config.spi.ConfigSource can be registered with a ConfigBuilder.
//-In this way, this user class is then included in later in configurations that the builder produces.
//	MySource source = new MySource();
//	builder.withSources(source, source2);

//	Java ServiceLoader loading of ConfigSources
//-The Java ServiceLoader pattern can also be used to locate custom configuration source objects.
// A user class that implements the ConfigSource interface is loaded if its full package qualified class name is listed
// in a file of the form, ${CLASSPATH}/META-INF/services/org.eclipse.microprofile.config.spi.ConfigSource.

@WebServlet("/configurationSources")
public class ConfigurationSourcesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Configuration - ConfigurationSource</h3>");
		
		Config config = ConfigProvider.getConfig();
		
		out.println("Available custom ConfigSources:");
		Iterator<ConfigSource> configSources = config.getConfigSources().iterator();
		while(configSources.hasNext()) {
			ConfigSource next = configSources.next();
			if(!next.toString().startsWith("fish.")) {
				out.println("<br/>Custom ConfigSource -> " + next);
			}
		}
		
		out.println("<br/><br/>System Property 'java.version' -> " +  config.getOptionalValue("java.version", String.class).orElse("NOT AVAILABLE"));
		out.println("<br/><br/>Windows Environmet Property 'TEST' -> " +  config.getOptionalValue("TEST", String.class).orElse("NOT AVAILABLE"));
		out.println("<br/><br/>Property from 'META-INF/microprofile-config.properties' -> " +  config.getValue("my.long.property", Long.class));
		out.println("<br/><br/>Property 'one' from custom CustomConfigSourceMy -> " +  config.getOptionalValue("one", String.class).orElse("NOT AVAILABLE"));		
		
    }

}
