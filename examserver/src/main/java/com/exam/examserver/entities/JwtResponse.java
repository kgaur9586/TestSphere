package com.exam.examserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO returned after successful authentication containing the JWT token.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

	private String token;

}
