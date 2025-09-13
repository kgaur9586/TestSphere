package com.exam.examserver.entities;

import org.springframework.security.core.GrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a single granted authority/role for Spring Security.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {

	private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}
}
