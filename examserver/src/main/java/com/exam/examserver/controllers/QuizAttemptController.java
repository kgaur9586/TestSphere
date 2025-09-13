package com.exam.examserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.examserver.entities.exam.QuizAttempt;
import com.exam.examserver.services.QuizAttemptService;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizAttemptController {

	@Autowired
	private QuizAttemptService quizAttemptService;

	@GetMapping("/{quizId}/attempt/{userId}")
	public ResponseEntity<?> attemptQuiz(@PathVariable("quizId") String quizId,
			@PathVariable("userId") String userId) {
		quizAttemptService.recordAttempt(quizId, userId);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/{quizId}/attempt-count")
	public ResponseEntity<Long> getAttemptCount(@PathVariable("quizId") String quizId) {
		return ResponseEntity.ok(this.quizAttemptService.getAttemptCount(quizId));
	}

	@GetMapping("/{quizId}/attempted-by")
	public ResponseEntity<List<QuizAttempt>> getAttemptsByQuizId(@PathVariable("quizId") String quizId) {
		return ResponseEntity.ok(this.quizAttemptService.getAllAttemptsOfQuiz(quizId));
	}
}
