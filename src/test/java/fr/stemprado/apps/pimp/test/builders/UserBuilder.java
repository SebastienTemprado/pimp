package fr.stemprado.apps.pimp.test.builders;

import fr.stemprado.apps.pimp.beans.entities.User;

public final class UserBuilder {

	private User instance;
	
	private UserBuilder() {
		instance = new User();
		instance.setUsername("johndoe");
		instance.setPassword("ab123456");
		instance.setLastname("doe");
		instance.setFirstname("john");
		instance.setEmail("johndoe@gmail.com");
	}
	
	public static UserBuilder init() {
		return new UserBuilder();
	}
	
	public UserBuilder username(String username) {
		instance.setUsername(username);
		return this;
	}
	
	public UserBuilder password(String password) {
		instance.setPassword(password);
		return this;
	}
	
	public UserBuilder lastname(String lastname) {
		instance.setLastname(lastname);
		return this;
	}
	
	public UserBuilder firstname(String firstname) {
		instance.setFirstname(firstname);
		return this;
	}
	
	public UserBuilder email(String email) {
		instance.setEmail(email);
		return this;
	}

	public User build() {
		return instance;
	}
}
