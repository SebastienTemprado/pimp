package fr.stemprado.apps.pimp.test.builders;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;

public final class UserDTOBuilder {

	private UserDTO instance;
	
	private UserDTOBuilder() {
		instance = new UserDTO();
		instance.setUsername("johndoe");
		instance.setPassword("ab123456");
		instance.setPasswordConfirmation("ab123456");
		instance.setLastname("doe");
		instance.setFirstname("john");
		instance.setEmail("johndoe@gmail.com");
	}
	
	public static UserDTOBuilder init() {
		return new UserDTOBuilder();
	}
	
	public UserDTOBuilder username(String username) {
		instance.setUsername(username);
		return this;
	}
	
	public UserDTOBuilder password(String password) {
		instance.setPassword(password);
		return this;
	}
	
	public UserDTOBuilder passwordConfirmation(String passwordConfirmation) {
		instance.setPasswordConfirmation(passwordConfirmation);
		return this;
	}
	
	public UserDTOBuilder lastname(String lastname) {
		instance.setLastname(lastname);
		return this;
	}
	
	public UserDTOBuilder firstname(String firstname) {
		instance.setFirstname(firstname);
		return this;
	}
	
	public UserDTOBuilder email(String email) {
		instance.setEmail(email);
		return this;
	}

	public UserDTO build() {
		return instance;
	}
}
