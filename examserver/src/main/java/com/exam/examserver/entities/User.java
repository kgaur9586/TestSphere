package com.exam.examserver.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

	@Id
	private String id;

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String profile;

	private boolean enable = true;

	// Now roles are simple strings
	private List<String> roles;
}
