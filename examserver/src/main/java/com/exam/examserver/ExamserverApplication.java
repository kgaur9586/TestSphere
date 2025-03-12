package com.exam.examserver;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.examserver.entities.Role;
import com.exam.examserver.entities.User;
import com.exam.examserver.entities.UserRole;
import com.exam.examserver.services.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner{
	
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
//		 System.out.println("Starting code ...");
//		 User user = new User();
//		 user.setFirstName("kapil");
//		 user.setLastName("Gaur");
//		 user.setEmail("Kapil@gmail.com");
//		 user.setPassword("abc");
//		 user.setUsername("kapil");
//		 user.setProfile("default");
//		 Role role = new Role();
//		 role.setRoleId(44L);
//		 role.setRoleName("Admin");
//	
//		 Set<UserRole> roles = new HashSet<UserRole>();
//		 UserRole userRole = new UserRole();
//		 userRole.setRole(role);
//		 userRole.setUser(user);
//		 roles.add(userRole);
//		 User user1 = this.userService.createUser(user, roles);
//		 System.out.print(user1.getUsername());
//		
	}

}


