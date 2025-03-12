package com.exam.examserver.services;

import java.util.List;

import com.exam.examserver.entities.exam.QuizAttempt;

public interface QuizAttemptService {
	public void recordAttempt(Long quizId, Long userId);
	public Long getAttemptCount(Long quizId);
	public List<Object[]> getAllAttemptsOfQuiz(Long quizId);
	
}
