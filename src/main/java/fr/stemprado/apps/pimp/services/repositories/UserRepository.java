package fr.stemprado.apps.pimp.services.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.stemprado.apps.pimp.beans.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);
}
