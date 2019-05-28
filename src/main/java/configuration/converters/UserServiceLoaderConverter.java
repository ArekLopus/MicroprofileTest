package configuration.converters;

import java.io.StringReader;
import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.eclipse.microprofile.config.spi.Converter;

import helper.UserEnum;
import helper.UserEnum.Type;

public class UserServiceLoaderConverter implements Converter<UserEnum> {

	@Override
	public UserEnum convert(String value) {
		
		UserEnum user = new UserEnum();
		
		JsonReader reader = Json.createReader(new StringReader(value));
		JsonObject jo = reader.readObject();
		
		user.setFirstName(jo.getString("firstName"));
		user.setLastName(jo.getString("lastName"));
		user.setDateOfBirth(LocalDate.parse(jo.getString("dateOfBirth")));
		user.setType(Type.valueOf(jo.getString("type")));
		
		return user;
	}

}
