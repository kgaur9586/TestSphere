package com.exam.examserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exam.examserver.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	User findByEmail(String email);


}
