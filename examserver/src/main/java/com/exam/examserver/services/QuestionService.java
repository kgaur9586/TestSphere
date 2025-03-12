package com.exam.examserver.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.entities.exam.Question;
import com.exam.examserver.entities.exam.Quiz;



public interface QuestionService {
	
	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestions();
	
	public Question getQuestion(Long questionId);
	
	public Set<Question> getQuestionOfQuiz(Quiz quiz);
	
	public void deleteQuestionById(Long questionId);
	public Question get(Long questionId);

}
