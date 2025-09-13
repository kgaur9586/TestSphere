package com.exam.examserver.services;

import java.util.List;
import java.util.Set;

import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.responseDto.QuizResponse;

public interface QuizService {

	Quiz addQuiz(Quiz quiz);

	Quiz updateQuiz(Quiz quiz);

	Set<Quiz> getAllQuizzes();

	Quiz getQuiz(String quizId);

	void deleteQuiz(String quizId);

	List<Quiz> getQuizzesOfCategory(String categoryId);

	List<Quiz> getActiveQuizzes();

	List<Quiz> getActiveQuizzesOfCategory(String categoryId);
	QuizResponse getQuizWithCategory(String quizId);
}
