package com.exam.examserver.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.exam.examserver.entities.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRoleName(String roleName);
}
