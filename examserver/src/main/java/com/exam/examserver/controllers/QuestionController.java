package com.exam.examserver.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import com.exam.examserver.entities.exam.Question;
import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.services.QuestionService;
import com.exam.examserver.services.QuizService;

import io.jsonwebtoken.lang.Collections;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestion(@PathVariable("quizId") Long quizId){
		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> allquestions = questionService.getQuestionOfQuiz(quiz);
		List<Question> list = new ArrayList(allquestions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
		    list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()));
		}
		list.forEach((q)->{
			q.setAnswer("");
		});

		java.util.Collections.shuffle(list);
		return ResponseEntity.ok(list);
		
	}
	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<?> getQuestionOfAdmin(@PathVariable("quizId") Long quizId){
		Quiz quiz = new Quiz();
		quiz.setQid(quizId);
		Set<Question> allquestions = this.questionService.getQuestionOfQuiz(quiz);
		
		return ResponseEntity.ok(allquestions);
		
	}
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(questionService.updateQuestion(question));
	}
	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable("questionId") Long questionId) {
		this.questionService.deleteQuestionById(questionId);
	}
	@GetMapping("/{quesId}")
	public Question get(@PathVariable("quesId") Long quesId) {
		return this.questionService.getQuestion(quesId);
	}
	
	//evaluating quiz
	
	@PostMapping("/eval/quiz")
	public ResponseEntity<?> evalQuz(@RequestBody List<Question> questions){
		System.out.println(questions);
		double marksGot= 0;
		int correctAnswers = 0;
		int attempted = 0;
		double marksSingle;
		for(Question q: questions) {
			Question question = this.questionService.get(q.getQuesId());
			if(question.getAnswer().equals(q.getSelectedAnswer())) {
				//correct answer
				correctAnswers++;
				marksSingle = Double.parseDouble(q.getQuiz().getMaxMarks()) / questions.size();
			    marksGot += marksSingle;
			}
			if(q.getSelectedAnswer() != "" || q.getSelectedAnswer() != null) {
				attempted++;
			}
		};
		Map<String, Object> answersMap = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
		return ResponseEntity.ok(answersMap);
	}
	
}

