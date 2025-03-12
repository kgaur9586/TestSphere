package com.exam.examserver.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.examserver.entities.User;
import com.exam.examserver.entities.UserRole;
import com.exam.examserver.repositories.RoleRepository;
import com.exam.examserver.repositories.UserRepository;
import com.exam.examserver.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder; // Add password encoder

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = userRepository.findByUsername(user.getUsername());
        User email_user = userRepository.findByEmail(user.getEmail());
        
        if(local != null || email_user != null) {
            throw new Exception("User already exists!");
        }
        
        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        for(UserRole ur : userRoles) {
            roleRepository.save(ur.getRole());
        }
        user.setUserRoles(userRoles);
        return userRepository.save(user);
    }

    // Keep other methods unchanged
    @Override
    @Transactional
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        
        if (existingUser == null) {
            throw new RuntimeException("User not found with username: " + user.getUsername());
        }
        
        // Preserve unchanged fields (e.g., password)
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.getPassword()); // Keep old password
        } else if (!user.getPassword().equals(existingUser.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode new password
        }
        
        // Preserve roles if not provided
        if (user.getUserRoles() == null || user.getUserRoles().isEmpty()) {
            user.setUserRoles(existingUser.getUserRoles());
        }

        return userRepository.save(user);
    }

}