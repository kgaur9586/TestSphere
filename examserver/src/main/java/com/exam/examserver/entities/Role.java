	package com.exam.examserver.entities;

	import java.util.HashSet;
	import java.util.Set;

	import com.fasterxml.jackson.annotation.JsonIgnore;
	import com.fasterxml.jackson.annotation.JsonManagedReference;

	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Entity;
	import jakarta.persistence.FetchType;
	import jakarta.persistence.Id;
	import jakarta.persistence.OneToMany;
	import jakarta.persistence.Table;
	import lombok.Data;

	@Data
	@Entity
	@Table(name="roles")
	public class Role {
			@Id
			private Long roleId;
			private String roleName;
			
			@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "role")
			@JsonManagedReference  // Prevent infinite recursion
			private Set<UserRole> userRoles = new HashSet<>();
	}
