package com.exam.examserver.repositories;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exam.examserver.entities.exam.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {

	Set<Question> findByQuizId(String quizId); // find questions by quizId
}
