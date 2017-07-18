package fr.stemprado.apps.pimp.mappers;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;
import fr.stemprado.apps.pimp.beans.entities.User;

public class UserMapper {

	public static User toUser(final UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setLastname(userDTO.getLastname());
		user.setFirstname(userDTO.getFirstname());
		user.setEmail(userDTO.getEmail());
		return user;
	}

	public static UserDTO toUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setLastname(user.getLastname());
		userDTO.setFirstname(user.getFirstname());
		userDTO.setEmail(user.getEmail());
		return userDTO;
	}
}
