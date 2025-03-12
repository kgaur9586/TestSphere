package com.exam.examserver.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.entities.exam.Quiz;


public interface QuizService{
	
	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Set<Quiz> getAllQuizzes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
	public List<Quiz> getQuizzesOfCategory(Category category);
	
	public List<Quiz> getActiveQuizzes();
	public List<Quiz> getActiveQuizzesOfCategory(Category category);
}
