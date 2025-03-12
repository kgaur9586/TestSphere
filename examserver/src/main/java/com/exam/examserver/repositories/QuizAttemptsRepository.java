package com.exam.examserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.entities.exam.QuizAttempt;

@Repository
public interface QuizAttemptsRepository extends JpaRepository<QuizAttempt, Long> {
	
	@Query("Select count(q) from QuizAttempt q where q.quiz.qid = :qId")
	long countAttemptsBYQuiz(@Param("qId") Long qId);
	
	@Query("SELECT qa.user, qa.attemptTime FROM QuizAttempt qa WHERE qa.quiz = :quiz")
	List<Object[]> findAttemptsByQuiz(@Param("quiz") Quiz quiz);

}
