package com.exam.examserver.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.entities.Role;
import com.exam.examserver.entities.User;
import com.exam.examserver.entities.UserRole;
import com.exam.examserver.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	// creating user
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		System.out.println("Received User: " + user);
		Set<UserRole> roles = new HashSet<UserRole>();
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("Normal");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		return this.userService.createUser(user, roles);
	}
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}
	@PutMapping("/")
	public ResponseEntity<?> updateUser(@RequestBody() User user){
		return ResponseEntity.ok(this.userService.updateUser(user));
	}
	
	
}
