package com.exam.examserver.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.entities.User;
import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.entities.exam.QuizAttempt;
import com.exam.examserver.repositories.QuizAttemptsRepository;
import com.exam.examserver.repositories.QuizRepository;
import com.exam.examserver.repositories.UserRepository;
import com.exam.examserver.services.QuizAttemptService;

@Service
public class QuizAttemptServiceImplementation implements QuizAttemptService {
	
	@Autowired 
	private QuizAttemptsRepository quizAttemptsRepository;
	
	@Autowired
	private QuizRepository quizRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public void recordAttempt(Long quizId, Long userId) {
		Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
		
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		
		QuizAttempt attempt = new QuizAttempt();
		attempt.setQuiz(quiz);
		attempt.setUser(user);
		attempt.setAttemptTime(LocalDateTime.now());
	
		
		quizAttemptsRepository.save(attempt);

	}

	@Override
	public Long getAttemptCount(Long quizId) {
		
		return quizAttemptsRepository.countAttemptsBYQuiz(quizId);
	}

	@Override
	public List<Object[]> getAllAttemptsOfQuiz(Long quizId) {
		Quiz quiz = quizRepository.findById(quizId)
	            .orElseThrow(() -> new RuntimeException("Quiz not found"));
		List<Object[]> attempts = quizAttemptsRepository.findAttemptsByQuiz(quiz);

	    return quizAttemptsRepository.findAttemptsByQuiz(quiz);
	}



	

	

}
