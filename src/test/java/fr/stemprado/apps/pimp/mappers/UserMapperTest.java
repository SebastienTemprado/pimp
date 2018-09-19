package fr.stemprado.apps.pimp.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;
import fr.stemprado.apps.pimp.beans.entities.User;
import fr.stemprado.apps.pimp.test.builders.UserBuilder;
import fr.stemprado.apps.pimp.test.builders.UserDTOBuilder;

public class UserMapperTest {

	@Test
	public void toUser() {
		final UserDTO userDTO = UserDTOBuilder.init().build();
		
		User result = UserMapper.toUser(userDTO);
		
		assertThat(result.getUsername()).isEqualTo(userDTO.getUsername());
		assertThat(result.getPassword()).isEqualTo(userDTO.getPassword());
		assertThat(result.getLastname()).isEqualTo(userDTO.getLastname());
		assertThat(result.getFirstname()).isEqualTo(userDTO.getFirstname());
		assertThat(result.getEmail()).isEqualTo(userDTO.getEmail());
	}
	
	@Test
	public void toUserDTO() {
		final User user = UserBuilder.init().build();
		
		UserDTO result = UserMapper.toUserDTO(user);
		
		assertThat(result.getUsername()).isEqualTo(user.getUsername());
		assertThat(result.getPassword()).isEqualTo(user.getPassword());
		assertThat(result.getLastname()).isEqualTo(user.getLastname());
		assertThat(result.getFirstname()).isEqualTo(user.getFirstname());
		assertThat(result.getEmail()).isEqualTo(user.getEmail());
	}
}
