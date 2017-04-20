package fr.stemprado.apps.pimp.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;
import fr.stemprado.apps.pimp.mappers.UserMapper;
import fr.stemprado.apps.pimp.services.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public void addGreet(@RequestBody UserDTO userDTO) {
		userService.addUser(UserMapper.toUser(userDTO));
	}
}
