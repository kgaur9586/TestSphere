package com.exam.examserver.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.exam.examserver.entities.exam.QuizAttempt;

@Repository
public interface QuizAttemptsRepository extends MongoRepository<QuizAttempt, String> {

	long countByQuizId(String quizId);

	List<QuizAttempt> findByQuizId(String quizId); // get all attempts by quizId
}
