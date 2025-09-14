package com.exam.examserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.exam.examserver.entities.User;
import com.exam.examserver.services.UserService;

@SpringBootApplication
@EnableCaching
public class ExamserverApplication implements CommandLineRunner {

	private final UserService userService;

	@Autowired
	public ExamserverApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Bootstrap default admin user if it does not exist
		if (userService.getUser("admin") == null) {
			User adminUser = new User();
			adminUser.setUsername("admin");
			adminUser.setPassword("admin123"); // will be encoded by UserService
			adminUser.setFirstName("Admin");
			adminUser.setLastName("User");
			adminUser.setEmail("admin@example.com");
			adminUser.setProfile("default");
			adminUser.setRoles(List.of("Admin")); // assign roles directly
			userService.createUser(adminUser);

			System.out.println("Default admin user created!");
		} else {
			System.out.println("Admin user already exists, skipping creation.");
		}
	}
}
