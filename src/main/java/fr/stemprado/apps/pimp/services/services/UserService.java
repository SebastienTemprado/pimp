package fr.stemprado.apps.pimp.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.stemprado.apps.pimp.beans.entities.User;
import fr.stemprado.apps.pimp.services.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
}
