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
import fr.stemprado.apps.pimp.constants.Properties;
import fr.stemprado.apps.pimp.constants.Views;
import fr.stemprado.apps.pimp.constants.api.AuthenticationApi;
import fr.stemprado.apps.pimp.constants.api.HomeApi;
import fr.stemprado.apps.pimp.services.constants.api.UserApi;

@Controller
public class HomeController {

	@Value(Properties.Rest.RESOURCES_URL_BASE)
	private String REST_RESOURCES_URL;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(HomeApi.PIMP)
	public String pimp() {
		return Views.Home.PIMP;
	}
	
	@RequestMapping(HomeApi.ROOT)
	public String home() {
		return Views.Home.PIMP;
	}
	
	@RequestMapping(AuthenticationApi.LOGIN)
	public String login() {
		return Views.Authentication.LOGIN;
	}
	
	@RequestMapping(AuthenticationApi.LOGGED_IN)
	public String signin() {
		System.out.println("logged In");
		return Views.Home.PIMP;
	}
	
	@RequestMapping(AuthenticationApi.SIGN_UP_FORM)
	public String signupForm(@ModelAttribute UserDTO userDTO) {
		System.out.println("signupForm");
		return Views.Authentication.SIGN_UP_FORM;
	}
	
	@RequestMapping(AuthenticationApi.SIGN_UP)
	public String signup(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, Locale locale) {
		System.out.println("signup");
		
		//TODO IT 
		if (!userDTO.getPassword().equals(userDTO.getPasswordConfirmation())) {
			bindingResult.rejectValue("passwordConfirmation", "error.userDTO", messageSource.getMessage("passwordConfirmation-matching-error", null, locale));
		}
		
		// TODO : basic passwords like '123456' or 'password' are forbidden
		if (bindingResult.hasErrors()) {
            return Views.Authentication.SIGN_UP_FORM;
        }
		else {
			userDTO.setPassword(DigestUtils.md5Hex(userDTO.getPassword()));
			restTemplate.postForObject(REST_RESOURCES_URL + UserApi.ADD_USER, userDTO, UserDTO.class);
		}
		
		return Views.Authentication.LOGIN;
	}
	
}
