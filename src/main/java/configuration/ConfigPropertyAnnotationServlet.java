package configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.config.inject.ConfigProperty;

//		@ConfigProperty
//-Binds the injection point with a configured value.
//-Injected values are the same values that would be retrieved from an injected Config instance
// or from the instance retrieved from ConfigProvider.getConfig()

//-Can be used to annotate injection points of type TYPE, Optional<TYPE> or javax.inject.Provider<TYPE>,
// where TYPE can be String and all types which have appropriate converters.
//-If @ConfigProperty is used with a type where no Converter exists, a deployment error will be thrown.
//-@ConfigProperty accepts a defaultValue param that will be used to set the field if the property is not present in the configuration.

//	Injecting Native Values
//-The first sample injects the configured value of the my.long.property property. 
//-The injected value does not change even if the underline property value changes in the Config.
//-Injecting a native value is recommended for a mandatory property and its value does not change at runtime or used by a bean with RequestScoped.
//-A further recommendation is to use the built in META-INF/microprofile-config.properties file mechanism to provide default values inside an App.
//-If no configured value exists for this property, a DeplymentException will be thrown during startup.
//	 @Inject
//	 @ConfigProperty(name="my.long.property")
//	 private Long injectedLongValue;

//	Injecting Optional Values
//-Contrary to natively injecting, if the property is not specified, this will not lead to a DeploymentException. 
//-The code injects a Long value to the my.optional.long.property. If the property does not exist, the value 123 will be assigned.
//	 @Inject
//	 @ConfigProperty(name="my.optional.long.property", defaultValue="123")
//	 private Long injectedLongValue;
 
//	Injecting Dynamic Values
//-The next sample injects a Provider for the value of my.long.property property to resolve the property dynamically.
//-Each invocation to Provider#get() will resolve the latest value from underlying Config again.
//-The existence of configured values will get checked during startup. Instances of Provider<T> are guaranteed to be Serializable.
//	 @Inject
//	 @ConfigProperty(name = "my.long.property" defaultValue="123")
//	 private Provider<Long> longConfigValue;


@WebServlet("/configurationConfigProperty")
public class ConfigPropertyAnnotationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    @ConfigProperty(name="my.long.property")
    private Long injectedLongValueNative;
    
    @Inject
	@ConfigProperty(name = "my.optional.int.property", defaultValue="123")
	private Optional<Integer> intConfigValueOptional;
    
    @Inject
	@ConfigProperty(name = "my.optional.int.propertyy", defaultValue="123")
	private Optional<Integer> notExistingintConfigValueOptional;
    
    @Inject
    @ConfigProperty(name = "random.int.property", defaultValue="123")		// Uses RandomIntConfigSource
    private Provider<Integer> intConfigValueDynamic;
    
    @Inject
    @ConfigProperty(name = "random.long.property", defaultValue="123")
    private Integer intConfigValueDynamic2;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Configuration - @ConfigProperty</h3>");
		
		out.println("Injecting Native Values -> " +  injectedLongValueNative);
		
		out.println("<br/><br/>Injecting Optional Values -> " +  intConfigValueOptional.get());
		out.println("<br/>Injecting Optional Values (Not Existing) -> " +  notExistingintConfigValueOptional.get());
		
		out.println("<br/><br/>Injecting Dynamic Values -> " +  intConfigValueDynamic.get());
		out.println("<br/>The same property if no Provider type -> " +  intConfigValueDynamic2);
    }

}
