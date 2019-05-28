package configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.eclipse.microprofile.config.spi.ConfigSource;

//-Config interface is used to get property value manually
//-It can be injected or created from ConfigProvider or ConfigBuilder.

//-Unlike the ConfigProvider interface, the ConfigBuilder interface initially has an empty set of configuration property sources.
// It is needed to add the default sources or / and custom sources
//	builder.addDefaultSources();

//-Resolves the property value by searching through all configured ConfigSources.
//-If the same property is specified in multiple ConfigSources, the value in the ConfigSource with the highestordinal will be used. 
//-If multiple ConfigSources are specified with the same ordinal, the ConfigSource.getName() will be used for sorting. 

//-The config objects produced via the injection model		@Inject Config
// are guaranteed to be serializable, while the programmatically created ones are not required to be serializable. 

//	Usage
//-For accessing the config you can use the ConfigProvider:  public void doSomething(
//	Config cfg = ConfigProvider.getConfig();
//	String archiveUrl = cfg.getValue("my.project.archive.endpoint", String.class);
//	Integer archivePort = cfg.getOptionalValue("my.project.archive.port", Integer.class).get();

//-It is also possible to inject the Config if a DI container is available: 
// public class MyService {
//	 @Inject
//	 private Config config;
// }

@WebServlet("/configurationConfig")
public class ConfigInterfaceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
	Config config1;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Configuration - Config Inferface</h3>");
		
		Config config2 = ConfigProvider.getConfig();
		
		//Warning: Check Permission failed in lookup for permission = ("org.glassfish.security.services.common.SecureServiceAccessPermission" "security/service/credential/provider/jceks")
		ConfigBuilder builder = ConfigProviderResolver.instance().getBuilder();
		builder.addDefaultSources();
		Config config3 = builder.build();
		
		
		out.println("Property from Config 1: " + config1.getPropertyNames().iterator().next());
		out.println("<br/>Property from Config 2: " + config2.getPropertyNames().iterator().next());
		out.println("<br/>Property from Config 3: " + config3.getPropertyNames().iterator().next());
		
		
		out.println("<br/><br/>Available custom ConfigSources:");
		Iterator<ConfigSource> configSources = config2.getConfigSources().iterator();
		while(configSources.hasNext()) {
			ConfigSource next = configSources.next();
			if(!next.toString().startsWith("fish.")) {
				out.println("<br/>Custom ConfigSource -> " + next);
			}
		}
		
		String prop1 = config1.getValue("one", String.class);
		Optional<String> prop2 = config1.getOptionalValue("two", String.class);
		
		out.println("<br/><br/>Property 'one' from CustomConfigSourceMy -> " +  prop1);
		out.println("<br/>Property 'two' from CustomConfigSourceMy -> " +  prop2.get());
		
		out.println("<br/><br/>Property from 'META-INF/microprofile-config.properties' -> " +  config1.getValue("my.long.property", Long.class));
		
		out.println("<br/><br/>Windows Environmet Property 'TEST' -> " +  config1.getOptionalValue("TEST", String.class).orElse("NOT AVAILABLE"));
		
    }

}
