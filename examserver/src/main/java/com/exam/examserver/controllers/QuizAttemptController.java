package com.exam.examserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.services.QuizAttemptService;

@RestController
@RequestMapping("/api/quizzes")
public class QuizAttemptController {
	@Autowired
	private QuizAttemptService quizAttemptService;
	
	@GetMapping("/{quizId}/attempt/{userId}")
	public ResponseEntity<?> attemptQuiz(@PathVariable("quizId") long quizId, @PathVariable("userId") long userId){
		quizAttemptService.recordAttempt(quizId, userId);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	@GetMapping("/{quizId}/attempt-count")
	public ResponseEntity<?> getAttemptCount(@PathVariable("quizId") long quizId){

		
		
		return ResponseEntity.ok(this.quizAttemptService.getAttemptCount(quizId));
	}
	
	@GetMapping("/{quizId}/attempted-by")
	public ResponseEntity<?> getAttemptsByQuizId(@PathVariable("quizId") Long quizId){
		return ResponseEntity.ok(this.quizAttemptService.getAllAttemptsOfQuiz(quizId));
	}
}
