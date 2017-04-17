package fr.stemprado.apps.pimp.beans.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class UserDTO {

	/**
	 * username : the login
	 */
	@NotNull
	@Pattern(regexp="^[a-zA-Záàâäãåçéèêëíìîïñóòôöõúùûüýÿæœ]{1}[a-zA-Záàâäãåçéèêëíìîïñóòôöõúùûüýÿæœ0-9]{1,29}$")
	private String username;
	
	@NotNull
	@Pattern(regexp="^[a-zA-Z'\\-áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœ\"~&²{}#¹()\\[\\]`_^@°+=$£¨\\\\µ*%§!:/;.?,<>]{8,30}$")
	private String password;
	
	private String passwordConfirmation;
	
	@NotNull
    @Pattern(regexp="^[a-zA-Záàâäãåçéèêëíìîïñóòôöõúùûüýÿæœ]{1}[a-zA-Z'\\-áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœ ]{0,28}[a-zA-Záàâäãåçéèêëíìîïñóòôöõúùûüýÿæœ]{1}$")
	private String lastname;
	
	@NotNull
	@Pattern(regexp="^[a-zA-Záàâäãåçéèêëíìîïñóòôöõúùûüýÿæœ]{1}[a-zA-Z'\\-áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœ ]{0,28}[a-zA-Záàâäãåçéèêëíìîïñóòôöõúùûüýÿæœ]{1}$")
	private String firstname;
	
	@NotNull
    @Email
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
