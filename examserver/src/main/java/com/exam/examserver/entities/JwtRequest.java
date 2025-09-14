package com.exam.examserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for receiving username and password in login requests.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

	private String username;
	private String password;

}
