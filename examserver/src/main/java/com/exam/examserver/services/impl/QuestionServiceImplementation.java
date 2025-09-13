package com.exam.examserver.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.entities.exam.Question;
import com.exam.examserver.repositories.QuestionRepository;
import com.exam.examserver.services.QuestionService;

@Service
public class QuestionServiceImplementation implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(String questionId) {
		return this.questionRepository.findById(questionId).orElse(null);
	}

	@Override
	public Set<Question> getQuestionOfQuiz(String quizId) {
		return this.questionRepository.findByQuizId(quizId);
	}

	@Override
	public void deleteQuestionById(String questionId) {
		this.questionRepository.deleteById(questionId);
	}

	@Override
	public Question get(String questionId) {
		return this.questionRepository.findById(questionId).orElse(null);
	}
}
