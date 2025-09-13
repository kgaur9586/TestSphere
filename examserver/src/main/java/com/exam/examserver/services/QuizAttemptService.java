package com.exam.examserver.services;

import java.util.List;

import com.exam.examserver.entities.exam.QuizAttempt;

public interface QuizAttemptService {
	void recordAttempt(String quizId, String userId);

	Long getAttemptCount(String quizId);

	List<QuizAttempt> getAllAttemptsOfQuiz(String quizId);
}
