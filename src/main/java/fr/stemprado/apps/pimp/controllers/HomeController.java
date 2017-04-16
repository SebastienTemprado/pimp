package fr.stemprado.apps.pimp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;

@Controller
public class HomeController {

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
	public String signup(@ModelAttribute UserDTO userDTO) {
		System.out.println("signup");
		return "login";
	}
	
}
