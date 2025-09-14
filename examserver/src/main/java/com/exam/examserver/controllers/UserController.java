package com.exam.examserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.examserver.entities.User;
import com.exam.examserver.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Create User (with default role Normal)
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        // If roles list is empty or null, assign a default role
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(List.of("Normal")); // default role
        }
        return this.userService.createUser(user);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        try{
			return this.userService.getUser(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        this.userService.deleteUser(userId);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.updateUser(user));
    }
}
