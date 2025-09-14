package com.exam.examserver.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.exam.examserver.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);
}
