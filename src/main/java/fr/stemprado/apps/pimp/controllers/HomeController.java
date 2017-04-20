package fr.stemprado.apps.pimp.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;

@Controller
public class HomeController {

	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping("/pimp")
	public String pimp() {
		return "pimp";
	}
	
	@RequestMapping("/")
	public String home() {
		return "pimp";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/loggedIn")
	public String signin() {
		System.out.println("logged In");
		return "pimp";
	}
	
	@RequestMapping("/signupForm")
	public String signupForm(@ModelAttribute UserDTO userDTO) {
		System.out.println("signupForm");
		return "signupForm";
	}
	
	@RequestMapping("/signup")
	public String signup(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, Locale locale) {
		System.out.println("signup");
		
		//TODO IT 
		if (!userDTO.getPassword().equals(userDTO.getPasswordConfirmation())) {
			bindingResult.rejectValue("passwordConfirmation", "error.userDTO", messageSource.getMessage("passwordConfirmation-matching-error", null, locale));
		}
		
		// TODO : basic passwords like '123456' or 'password' are forbidden
		if (bindingResult.hasErrors()) {
            return "signupForm";
        }
		else {
			RestTemplate restTemplate = new RestTemplate();
			//TODO : encode password
			//TODO : get the base url from application.properties 
			restTemplate.postForObject("http://localhost:9296/addUser", userDTO, UserDTO.class);
		}
		
		return "login";
	}
	
}
