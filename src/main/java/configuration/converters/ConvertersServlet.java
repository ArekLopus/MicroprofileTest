package configuration.converters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.eclipse.microprofile.config.spi.Converter;

import helper.User;
import helper.UserEnum;


@WebServlet("/configurationConverters")
public class ConvertersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Configuration - Converters</h3>");
		
		//Throws when used in Servlet container:
		//Warning: Check Permission failed in lookup for permission = ("org.glassfish.security.services.common.SecureServiceAccessPermission" "security/service/credential/provider/jceks")
		ConfigBuilder builder = ConfigProviderResolver.instance().getBuilder();
		builder.addDefaultSources();
		Converter<User> userConverter = new UserConverter();
		builder.withConverters(userConverter);
		Config config = builder.build();
		
		User user = config.getValue("user.default", User.class);
		out.println("User Converted from META-INF/microprofile-config.properties -> " + user);
		
		
		//UserServiceLoaderConverter is found automatically from META-INF/services/org.eclipse.microprofile.config.spi.Converter file
		Config config2 = ConfigProvider.getConfig();
		UserEnum user2 = config2.getValue("user.enum", UserEnum.class);
		out.println("<br/><br/>UserEnum Converted from META-INF/microprofile-config.properties -> " + user2);
    }

}
