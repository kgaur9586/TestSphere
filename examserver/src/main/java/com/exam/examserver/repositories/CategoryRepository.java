package com.exam.examserver.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.exam.examserver.entities.exam.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
