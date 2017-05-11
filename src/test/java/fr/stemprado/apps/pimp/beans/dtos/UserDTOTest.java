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

import fr.stemprado.apps.pimp.test.builders.UserDTOBuilder;

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
        UserDTO user = UserDTOBuilder.init().lastname("D").build();
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameMaxLength() {
        UserDTO user = UserDTOBuilder.init().lastname("Abcdefghijklmnopqrstuvwxyzabcde").build();
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCanContainQuote() {
        UserDTO user = UserDTOBuilder.init().lastname("Abc'def").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void lastnameCanContainHyphen() {
    	UserDTO user = UserDTOBuilder.init().lastname("Abc-def").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void lastnameCanContainSpace() {
    	UserDTO user = UserDTOBuilder.init().lastname("Abc def").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantEndWithHyphen() {
    	UserDTO user = UserDTOBuilder.init().lastname("Abcdef-").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantStartWithHyphen() {
    	UserDTO user = UserDTOBuilder.init().lastname("-Abcdef").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantEndWithSpace() {
    	UserDTO user = UserDTOBuilder.init().lastname("Abcdef ").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantStartWithSpace() {
    	UserDTO user = UserDTOBuilder.init().lastname(" Abcdef").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantContainDigit() {
    	UserDTO user = UserDTOBuilder.init().lastname("Abc1def").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void lastnameCantContainSpecialChars() {
    	UserDTO user = UserDTOBuilder.init().lastname("A\"~&²{}#¹()`_^@°+=$£¨µd").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
        
        user = UserDTOBuilder.init().lastname("*%§!:[]/;.?,<>bc\\def").build(); 
        
        violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameMinLength() {
    	UserDTO user = UserDTOBuilder.init().firstname("J").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameMaxLength() {
    	UserDTO user = UserDTOBuilder.init().firstname("Abcdefghijklmnopqrstuvwxyzabcde").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCanContainQuote() {
    	UserDTO user = UserDTOBuilder.init().firstname("Abc'def").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void firstnameCanContainHyphen() {
    	UserDTO user = UserDTOBuilder.init().firstname("Abc-def").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void firstnameCanContainSpace() {
    	UserDTO user = UserDTOBuilder.init().firstname("Abc def").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantEndWithHyphen() {
    	UserDTO user = UserDTOBuilder.init().firstname("Abcdef-").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantStartWithHyphen() {
    	UserDTO user = UserDTOBuilder.init().firstname("-Abcdef").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantEndWithSpace() {
    	UserDTO user = UserDTOBuilder.init().firstname("Abcdef ").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantStartWithSpace() {
    	UserDTO user = UserDTOBuilder.init().firstname(" Abcdef").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantContainDigit() {
    	UserDTO user = UserDTOBuilder.init().firstname("Abc1def").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void firstnameCantContainSpecialChars() {
    	UserDTO user = UserDTOBuilder.init().firstname("A\"~&²{}#¹()`_^@°+=$").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
        
        user = UserDTOBuilder.init().firstname("a[]£¨µ*%§!:/;.?,<>bc\\def").build(); 
        
        violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameMinLength() {
    	UserDTO user = UserDTOBuilder.init().username("l").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameMaxLength() {
    	UserDTO user = UserDTOBuilder.init().username("login12345678901234567890123456").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCantContainQuote() {
    	UserDTO user = UserDTOBuilder.init().username("log'in").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCantContainHyphen() {
    	UserDTO user = UserDTOBuilder.init().username("log-in").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCantContainSpace() {
    	UserDTO user = UserDTOBuilder.init().username("log in").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCantContainSpecialChars() {
    	UserDTO user = UserDTOBuilder.init().username("A\"~&²{}#¹()[]`_^@°+=$").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
        
        user = UserDTOBuilder.init().username("a£¨µ*%§!:/;.?,<>bc\\def").build(); 
        
        violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void usernameCanEndWithDigit() {
    	UserDTO user = UserDTOBuilder.init().username("login123").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void passwordMinLength() {
    	UserDTO user = UserDTOBuilder.init().password("passwor").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void passwordMaxLength() {
    	UserDTO user = UserDTOBuilder.init().password("password12345678901234567890123").passwordConfirmation("password12345678901234567890123").build(); 
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
    @Test
    public void passwordCanContainSpecialChars() {
    	UserDTO user = UserDTOBuilder.init().password("\"~&²{}#¹()`_^@°+=$").passwordConfirmation("\"~&²{}#¹()`_^@°+=$").build();
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
        
        user = UserDTOBuilder.init().password("£¨µ*%§![]:/;.?,<>bc\\").passwordConfirmation("£¨µ*%§![]:/;.?,<>bc\\").build();
        
        violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void passwordCanContainDigits() {
    	UserDTO user = UserDTOBuilder.init().password("15479477").passwordConfirmation("15479477").build();
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void invalidEmail() {
    	UserDTO user = UserDTOBuilder.init().email("aaaa.fr").build();
        
        Set<ConstraintViolation<UserDTO>> violations = this.validator.validate(user);
        
        assertFalse(violations.isEmpty());
    }
    
}
