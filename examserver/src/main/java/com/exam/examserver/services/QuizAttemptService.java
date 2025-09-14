package com.exam.examserver.services;

import java.util.List;

import com.exam.examserver.responseDto.QuizAttemptResponse;

public interface QuizAttemptService {
	void recordAttempt(String quizId, String userId);

	Long getAttemptCount(String quizId);

	List<QuizAttemptResponse> getAllAttemptsOfQuiz(String quizId);
}
