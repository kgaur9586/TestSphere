package com.exam.examserver.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.services.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
	@GetMapping("/")
	public ResponseEntity<Set<Quiz>> getQuizzes(){
		return ResponseEntity.ok(this.quizService.getAllQuizzes());
	}
	
	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Long quizId){
		return ResponseEntity.ok(this.quizService.getQuiz(quizId));
	}
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable("quizId") Long quizId) {
		this.quizService.deleteQuiz(quizId);
	}
	@GetMapping("/category/{cId}")
	public ResponseEntity<?> getQuizzesOfCategory(@PathVariable("cId") Long cId){
		Category category = new Category();
		category.setCid(cId);
		return ResponseEntity.ok(this.quizService.getQuizzesOfCategory(category));
	}
	
	@GetMapping("/active")
	public ResponseEntity<?> getActiveQuizzes(){
		return ResponseEntity.ok(this.quizService.getActiveQuizzes());
	}
	
	@GetMapping("/category/active/{cId}")
	public ResponseEntity<?> getActiveQuizzesOfCategory(@PathVariable("cId") Long cId){
		Category c = new Category();
		c.setCid(cId);
		return ResponseEntity.ok(this.quizService.getActiveQuizzesOfCategory(c));
	}
	
	
	
}
