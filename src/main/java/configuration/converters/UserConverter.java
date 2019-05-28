package configuration.converters;

import java.io.StringReader;
import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.eclipse.microprofile.config.spi.Converter;

import helper.User;

public class UserConverter implements Converter<User> {

	@Override
	public User convert(String value) {
		
		User user = new User();
		
		JsonReader reader = Json.createReader(new StringReader(value));
		JsonObject jo = reader.readObject();
		
		user.setFirstName(jo.getString("firstName"));
		user.setLastName(jo.getString("lastName"));
		user.setDateOfBirth(LocalDate.parse(jo.getString("dateOfBirth")));
		
		return user;
	}

}
