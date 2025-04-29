package com.graduationProject.graduationProject.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.graduationProject.graduationProject.entities.concretes.User;

@Repository
public interface UserDao extends CrudRepository<User, Long>{

	Optional<User> findByEmail(String email);
	
}
