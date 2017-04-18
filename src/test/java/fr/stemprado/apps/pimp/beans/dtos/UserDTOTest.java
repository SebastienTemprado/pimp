package fr.stemprado.apps.pimp.beans.dtos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

//TODO : create an UserDTO builder for tests
public class UserDTOTest {
	
	private Validator validator;

    @Before
    public void init() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @Test
    public void lastnameMinLength() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("D");
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameMaxLength() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Abcdefghijklmnopqrstuvwxyzabcde"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCanContainQuote() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Abc'def"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void lastnameCanContainHyphen() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Abc-def"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void lastnameCanContainSpace() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Abc def"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantEndWithHyphen() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Abcdef-"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantStartWithHyphen() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("-Abcdef"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantEndWithSpace() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Abcdef "); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantStartWithSpace() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname(" Abcdef"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantContainDigit() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Abc1def"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantContainSpecialChars() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("A\"~&²{}#¹()`_^@°+=$£¨µd"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
        
        user.setLastname("*%§!:[]/;.?,<>bc\\def"); 
        
        violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameMinLength() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("J");
        user.setLastname("Doe");
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameMaxLength() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("Abcdefghijklmnopqrstuvwxyzabcde");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCanContainQuote() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("Abc'def");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void firstnameCanContainHyphen() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("Abc-def");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void firstnameCanContainSpace() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("Abc def");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantEndWithHyphen() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("Abcdef-");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantStartWithHyphen() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("-Abcdef");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantEndWithSpace() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("Abcdef ");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantStartWithSpace() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname(" Abcdef");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantContainDigit() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("Abc1def");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantContainSpecialChars() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("A\"~&²{}#¹()`_^@°+=$");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
        
        user.setFirstname("a[]£¨µ*%§!:/;.?,<>bc\\def");
        
        violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameMinLength() {
        UserDTO user = new UserDTO();
        user.setUsername("l");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Doe");
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameMaxLength() {
        UserDTO user = new UserDTO();
        user.setUsername("login12345678901234567890123456");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Doe");
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCantContainQuote() {
        UserDTO user = new UserDTO();
        user.setUsername("log'in");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Doe");
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCantContainHyphen() {
        UserDTO user = new UserDTO();
        user.setUsername("log-in");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Doe");
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCantContainSpace() {
        UserDTO user = new UserDTO();
        user.setUsername("log in");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Doe");
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCantContainSpecialChars() {
        UserDTO user = new UserDTO();
        user.setUsername("A\"~&²{}#¹()[]`_^@°+=$");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("John");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
        
        user.setUsername("a£¨µ*%§!:/;.?,<>bc\\def");
        
        violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCanEndWithDigit() {
        UserDTO user = new UserDTO();
        user.setUsername("login123");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Doe");
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void passwordMinLength() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("passwor");
        user.setPasswordConfirmation("passwor");
        user.setFirstname("john");
        user.setLastname("Doe");
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void passwordMaxLength() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password12345678901234567890123");
        user.setPasswordConfirmation("password12345678901234567890123");
        user.setFirstname("john");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void passwordCanContainSpecialChars() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("\"~&²{}#¹()`_^@°+=$");
        user.setPasswordConfirmation("\"~&²{}#¹()`_^@°+=$");
        user.setFirstname("john");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
        
        user.setPassword("£¨µ*%§![]:/;.?,<>bc\\");
        user.setPasswordConfirmation("£¨µ*%§![]:/;.?,<>bc\\");
        
        violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void passwordCanContainDigits() {
        UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("15479477");
        user.setPasswordConfirmation("15479477");
        user.setFirstname("john");
        user.setLastname("Doe"); 
        user.setEmail("aa@aa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void invalidEmail() {
    	UserDTO user = new UserDTO();
        user.setUsername("login");
        user.setPassword("password");
        user.setPasswordConfirmation("password");
        user.setFirstname("john");
        user.setLastname("Doe"); 
        user.setEmail("aaaa.fr");
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
}
