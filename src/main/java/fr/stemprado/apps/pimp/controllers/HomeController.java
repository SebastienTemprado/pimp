package fr.stemprado.apps.pimp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
