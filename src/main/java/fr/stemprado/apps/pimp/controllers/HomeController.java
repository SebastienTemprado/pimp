package fr.stemprado.apps.pimp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stemprado.apps.pimp.constants.Views;
import fr.stemprado.apps.pimp.constants.api.HomeApi;

@Controller
public class HomeController {

	private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(HomeApi.PIMP)
	public String pimp() {
		logger.debug("pimp");
		return Views.Home.PIMP;
	}
	
	@RequestMapping(HomeApi.ROOT)
	public String home() {
		logger.debug("home");
		return Views.Home.PIMP;
	}
	
}
