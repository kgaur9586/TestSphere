package com.exam.examserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
