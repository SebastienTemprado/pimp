package fr.stemprado.apps.pimp.services.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;
import fr.stemprado.apps.pimp.beans.entities.User;
import fr.stemprado.apps.pimp.mappers.UserMapper;
import fr.stemprado.apps.pimp.services.constants.api.UserApi;
import fr.stemprado.apps.pimp.services.services.UserService;

@RestController
public class UserController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value=UserApi.ADD_USER, method = RequestMethod.POST)
	public void addUser(@RequestBody UserDTO userDTO) {
		User user = UserMapper.toUser(userDTO);
		logger.debug("Call userService.addUser() with the user : {}", userDTO);
		userService.addUser(user);
	}
	
	//TODO : to secure even if the password is encoded ?
	@RequestMapping(value=UserApi.GET_USER, method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable(value="username") String username) {
		logger.debug("Call userService.getUser() with the username : {}", username);
		User user = (User) userService.loadUserByUsername(username);
		return new ResponseEntity<UserDTO>(UserMapper.toUserDTO(user), HttpStatus.OK);
	}
}
