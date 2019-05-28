package helper;

import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;

public class User {
	
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	
	public static User DEFAULT_USER = new User("John", "Doe", LocalDate.of(1989, 2, 11));
	
	public User() {}
	
	public User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(String firstName, String lastName, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
	public JsonObject toJson() {
		return Json.createObjectBuilder().add("firstName", firstName).add("lastName", lastName).add("dateOfBirth", dateOfBirth.toString()).build();
	}
}
