package com.exam.examserver.controllers;

import java.util.Set;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.responseDto.QuizResponse;
import com.exam.examserver.services.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}

	@PutMapping("/update")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}

	@GetMapping("/")
	public ResponseEntity<Set<Quiz>> getQuizzes() {
		return ResponseEntity.ok(this.quizService.getAllQuizzes());
	}

	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") String quizId) {
		return ResponseEntity.ok(this.quizService.getQuiz(quizId));
	}
	@GetMapping("/withCategories/{quizId}")
	public ResponseEntity<QuizResponse> getQuizWithCategories(@PathVariable("quizId") String quizId) {
		return ResponseEntity.ok(this.quizService.getQuizWithCategory(quizId));
	}
	

	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable("quizId") String quizId) {
		this.quizService.deleteQuiz(quizId);
	}

	@GetMapping("/category/{cId}")
	public ResponseEntity<?> getQuizzesOfCategory(@PathVariable("cId") String cId) {
		return ResponseEntity.ok(this.quizService.getQuizzesOfCategory(cId));
	}

	@GetMapping("/active")
	public ResponseEntity<?> getActiveQuizzes() {
		return ResponseEntity.ok(this.quizService.getActiveQuizzes());
	}

	@GetMapping("/category/active/{cId}")
	public ResponseEntity<?> getActiveQuizzesOfCategory(@PathVariable("cId") String cId) {
		return ResponseEntity.ok(this.quizService.getActiveQuizzesOfCategory(cId));
	}
}
