package fr.stemprado.apps.pimp.it.services.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import fr.stemprado.apps.pimp.Pimp;
import fr.stemprado.apps.pimp.beans.dtos.UserDTO;
import fr.stemprado.apps.pimp.services.constants.api.UserApi;
import fr.stemprado.apps.pimp.test.builders.UserDTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes={Pimp.class})
public class UserControllerIT {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void addAndReadUser() {
		UserDTO userDTO = UserDTOBuilder.init().username("addUserIT").build();
		
		restTemplate.postForObject(UserApi.ADD_USER, userDTO, UserDTO.class);
		UserDTO result = restTemplate.getForObject(UserApi.GET_USER, UserDTO.class, userDTO.getUsername());
		
		assertThat(result.getUsername()).isEqualTo(userDTO.getUsername());
		assertThat(result.getLastname()).isEqualTo(userDTO.getLastname());
		assertThat(result.getFirstname()).isEqualTo(userDTO.getFirstname());
		assertThat(result.getEmail()).isEqualTo(userDTO.getEmail());
	}
}
