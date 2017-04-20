package fr.stemprado.apps.pimp.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;
import fr.stemprado.apps.pimp.beans.entities.User;
import fr.stemprado.apps.pimp.services.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public void addGreet(@RequestBody UserDTO userDTO) {
		User user = new User();
		// TODO mapper
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setLastname(userDTO.getLastname());
		user.setFirstname(userDTO.getFirstname());
		user.setEmail(userDTO.getEmail());
		userService.addUser(user);
	}
}
