package com.exam.examserver.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.exam.examserver.config.JwtUtils;
import com.exam.examserver.entities.JwtRequest;
import com.exam.examserver.entities.JwtResponse;
import com.exam.examserver.entities.User;
import com.exam.examserver.services.impl.UserDetailServiceImpl;
import com.exam.examserver.services.impl.UserServiceImplementation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Autowired
	private UserServiceImplementation userService;
	@Autowired
	private JwtUtils jwtUtils;

	// Generate token
	@PostMapping("/user/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							jwtRequest.getUsername(),
							jwtRequest.getPassword()));

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String token = jwtUtils.generateToken(userDetails);
			return ResponseEntity.ok(new JwtResponse(token));

		} catch (BadCredentialsException e) {
			return ResponseEntity.status(401).body("Invalid credentials");
		} catch (DisabledException e) {
			return ResponseEntity.status(403).body("User disabled");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Authentication error: " + e.getMessage());
		}
	}

	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return userService.getUser(principal.getName());
	}


}
