package com.exam.examserver.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.examserver.entities.exam.Question;
import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.responseDto.QuestionResponse;
import com.exam.examserver.services.QuestionService;
import com.exam.examserver.services.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}

	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestion(@PathVariable("quizId") String quizId) {
		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> allQuestions = questionService.getQuestionOfQuiz(quizId);
		List<Question> list = new ArrayList<>(allQuestions);

		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()));
		}
		list.forEach((q) -> q.setAnswer("")); // hide answer

		java.util.Collections.shuffle(list);

		QuestionResponse response = new QuestionResponse(quiz, list);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<?> getQuestionOfAdmin(@PathVariable("quizId") String quizId) {
		Set<Question> allQuestions = this.questionService.getQuestionOfQuiz(quizId);
		return ResponseEntity.ok(allQuestions);
	}

	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.updateQuestion(question));
	}

	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable("questionId") String questionId) {
		this.questionService.deleteQuestionById(questionId);
	}

	@GetMapping("/{quesId}")
	public Question get(@PathVariable("quesId") String quesId) {
		return this.questionService.getQuestion(quesId);
	}

	// evaluating quiz
	@PostMapping("/eval/quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
		try {
			double marksGot = 0;
			int correctAnswers = 0;
			int attempted = 0;

			if (questions.isEmpty()) {
				return ResponseEntity.badRequest().body("No questions submitted.");
			}

			// Get quiz to fetch maxMarks and numberOfQuestions
			Question firstQuestion = this.questionService.get(questions.get(0).getId());
			if (firstQuestion == null) {
				return ResponseEntity.badRequest().body("Invalid question data.");
			}
			Quiz quiz = this.quizService.getQuiz(firstQuestion.getQuizId());
			double marksPerQuestion = 1.0;
			if (quiz != null) {
				try {
					marksPerQuestion = Double.parseDouble(quiz.getMaxMarks())
							/ Double.parseDouble(quiz.getNumberOfQuestions());
				} catch (Exception e) {
					marksPerQuestion = 1.0;
				}
			}

			for (Question q : questions) {
				Question question = this.questionService.get(q.getId());
				if (question != null && question.getAnswer().trim()
						.equals(q.getSelectedAnswer() != null ? q.getSelectedAnswer().trim() : "")) {
					correctAnswers++;
					marksGot += marksPerQuestion;
				}
				if (q.getSelectedAnswer() != null && !q.getSelectedAnswer().isEmpty()) {
					attempted++;
				}
			}

			Map<String, Object> answersMap = Map.of(
					"marksGot", marksGot,
					"correctAnswers", correctAnswers,
					"attempted", attempted);
			return ResponseEntity.ok(answersMap);
		} catch (Exception e) {
			System.out.println("Error while evaluating quiz" + e);
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error while evaluating quiz" + e.getMessage());
		}
	}
}
