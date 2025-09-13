package com.exam.examserver.services;

import java.util.List;

import com.exam.examserver.entities.User;

public interface UserService {
	User createUser(User user) throws Exception;

	User getUser(String username);

	void deleteUser(String userId);

	List<User> getAllUsers();

	User updateUser(User user);
}
