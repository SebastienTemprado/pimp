package fr.stemprado.apps.pimp.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import fr.stemprado.apps.pimp.services.constants.api.UserApi;

@Controller
public class AuthenticationController {
	
	private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Value(Properties.Rest.RESOURCES_URL_BASE)
	private String REST_RESOURCES_URL;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(AuthenticationApi.LOGIN)
	public String login() {
		return Views.Authentication.LOGIN;
	}
	
	@RequestMapping(AuthenticationApi.LOGGED_IN)
	public String signin() {
		logger.debug("logged In");
		return Views.Home.PIMP;
	}
	
	@RequestMapping(AuthenticationApi.SIGN_UP_FORM)
	public String signupForm(@ModelAttribute UserDTO userDTO) {
		logger.debug("signupForm");
		return Views.Authentication.SIGN_UP_FORM;
	}
	
	@RequestMapping(AuthenticationApi.SIGN_UP)
	public String signup(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, Locale locale) {
		logger.debug("signup");
		
		if (!userDTO.getPassword().equals(userDTO.getPasswordConfirmation())) {
			logger.info("passwordConfirmation doesn't match password");
			bindingResult.rejectValue("passwordConfirmation", "error.userDTO", messageSource.getMessage("passwordConfirmation-matching-error", null, locale));
		}
		
		// TODO : basic passwords like '123456' or 'password' are forbidden
		if (bindingResult.hasErrors()) {
			logger.debug("Error in the sign up form");
            return Views.Authentication.SIGN_UP_FORM;
        }
		else {
			logger.debug("Call the service to add the user");
			userDTO.setPassword(DigestUtils.md5Hex(userDTO.getPassword()));
			restTemplate.postForObject(REST_RESOURCES_URL + UserApi.ADD_USER, userDTO, UserDTO.class);
		}
		
		return Views.Authentication.LOGIN;
	}
}
