package fr.stemprado.apps.pimp.beans.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

	/**
	 * username : the login
	 */
	@NotNull
    @Size(min=2, max=30)
	private String username;
	
	@NotNull
    @Size(min=2, max=30)
	private String password;
	
	@NotNull
    @Size(min=2, max=30)
	private String passwordConfirmation;
	
	@NotNull
    @Size(min=2, max=30)
	private String lastname;
	
	@NotNull
    @Size(min=2, max=30)
	private String firstname;
	
	@NotNull
    @Size(min=2, max=30)
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
