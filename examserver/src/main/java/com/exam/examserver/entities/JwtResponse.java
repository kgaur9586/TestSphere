package com.exam.examserver.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
	String token;
	public JwtResponse(String token2) {
		// TODO Auto-generated constructor stub
		this.token = token2;
	}

	
}
