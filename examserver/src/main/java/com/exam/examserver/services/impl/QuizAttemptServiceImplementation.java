package com.exam.examserver.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.entities.User;
import com.exam.examserver.entities.exam.QuizAttempt;
import com.exam.examserver.repositories.QuizAttemptsRepository;
import com.exam.examserver.repositories.UserRepository;
import com.exam.examserver.responseDto.QuizAttemptResponse;
import com.exam.examserver.services.QuizAttemptService;

@Service
public class QuizAttemptServiceImplementation implements QuizAttemptService {

	@Autowired
	private QuizAttemptsRepository quizAttemptsRepository;

    @Autowired
    private UserRepository userRepository;

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
	public List<QuizAttemptResponse> getAllAttemptsOfQuiz(String quizId) {
		List<QuizAttempt> attempts = quizAttemptsRepository.findByQuizId(quizId);
		List<String> userIds = attempts.stream()
			.map(QuizAttempt::getUserId)
			.filter(id -> id != null)
			.distinct()
			.collect(Collectors.toList());
		List<User> users = userRepository.findAllById(userIds);
		Map<String, User> userMap = new HashMap<>();
		for (User user : users) {
			userMap.put(user.getId(), user);
		}
		return attempts.stream()
			.map(attempt -> new QuizAttemptResponse(attempt, userMap.get(attempt.getUserId())))
			.collect(Collectors.toList());
	}
}
