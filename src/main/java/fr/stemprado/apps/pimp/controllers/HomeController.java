package fr.stemprado.apps.pimp.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;


//TODO views in a constant class
//TODO service endpoints in a constant class
@Controller
public class HomeController {

	@Value("${rest-resources-url}")
	private String REST_RESOURCES_URL;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private RestTemplate restTemplate;
	
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
			userDTO.setPassword(DigestUtils.md5Hex(userDTO.getPassword()));
			restTemplate.postForObject(REST_RESOURCES_URL + "addUser", userDTO, UserDTO.class);
		}
		
		return "login";
	}
	
}
