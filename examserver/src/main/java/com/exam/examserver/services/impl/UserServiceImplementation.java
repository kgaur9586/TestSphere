package com.exam.examserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.examserver.entities.User;
import com.exam.examserver.repositories.UserRepository;
import com.exam.examserver.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) throws Exception {
        // Check for duplicate username or email
        User existingByUsername = userRepository.findByUsername(user.getUsername());
        User existingByEmail = userRepository.findByEmail(user.getEmail());

        if (existingByUsername != null || existingByEmail != null) {
            throw new Exception("User already exists!");
        }

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user to MongoDB
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(String userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        // Fetch the existing user by username or id
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null) {
            throw new RuntimeException("User not found with username: " + user.getUsername());
        }

        // Preserve password if not updated
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        } else if (!user.getPassword().equals(existingUser.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        // Preserve roles if not provided
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(existingUser.getRoles());
        }

        // Keep MongoDB _id so update works properly
        user.setId(existingUser.getId());

        return userRepository.save(user);
    }
}
