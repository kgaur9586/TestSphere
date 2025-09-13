package com.exam.examserver.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exam.examserver.entities.exam.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {

	List<Quiz> findByCategoryId(String categoryId);

	List<Quiz> findByActive(Boolean active);

	List<Quiz> findByCategoryIdAndActive(String categoryId, Boolean active);
}
