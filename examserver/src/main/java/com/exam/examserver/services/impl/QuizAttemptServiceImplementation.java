package com.exam.examserver.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.entities.exam.QuizAttempt;
import com.exam.examserver.repositories.QuizAttemptsRepository;
import com.exam.examserver.services.QuizAttemptService;

@Service
public class QuizAttemptServiceImplementation implements QuizAttemptService {

	@Autowired
	private QuizAttemptsRepository quizAttemptsRepository;

	@Override
	public void recordAttempt(String quizId, String userId) {
		QuizAttempt attempt = new QuizAttempt();
		attempt.setQuizId(quizId);
		attempt.setUserId(userId);
		attempt.setAttemptTime(LocalDateTime.now());
		quizAttemptsRepository.save(attempt);
	}

	@Override
	public Long getAttemptCount(String quizId) {
		return quizAttemptsRepository.countByQuizId(quizId);
	}

	@Override
	public List<QuizAttempt> getAllAttemptsOfQuiz(String quizId) {
		return quizAttemptsRepository.findByQuizId(quizId);
	}
}
