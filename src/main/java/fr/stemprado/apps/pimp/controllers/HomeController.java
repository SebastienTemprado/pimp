package fr.stemprado.apps.pimp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stemprado.apps.pimp.constants.Views;
import fr.stemprado.apps.pimp.constants.api.HomeApi;

//TODO logger
@Controller
public class HomeController {

	@RequestMapping(HomeApi.PIMP)
	public String pimp() {
		return Views.Home.PIMP;
	}
	
	@RequestMapping(HomeApi.ROOT)
	public String home() {
		return Views.Home.PIMP;
	}
	
}
