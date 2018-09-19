package fr.stemprado.apps.pimp.services.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.stemprado.apps.pimp.beans.entities.User;
import fr.stemprado.apps.pimp.services.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userRepository.findByUsername(username);
		if (user == null) {
			logger.info("User {} not found.", username);
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return user;
	}
	
}
