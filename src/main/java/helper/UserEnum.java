package helper;

import java.time.LocalDate;

public class UserEnum {
	
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Type type;
	
	public static UserEnum DEFAULT_USER = new UserEnum("John", "Doe", LocalDate.of(1989, 2, 11), Type.MANAGER);
	
	public UserEnum() {}
	
	public UserEnum(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserEnum(String firstName, String lastName, LocalDate dateOfBirth, Type type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.type = type;
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
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	
	@Override
	public String toString() {
		return "UserEnum [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", type=" + type + "]";
	}




	public enum Type {
		EMPLOYEE,
		MANAGER,
		BOSS
	}
}
