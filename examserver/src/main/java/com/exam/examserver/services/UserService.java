package com.exam.examserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.exam.examserver.entities.User;
import com.exam.examserver.entities.UserRole;

public interface UserService {
	
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	public User getUser(String username);
	public void deleteUser(Long userId);
	public List<User> getAllUsers();
	public User updateUser(User user);
}
