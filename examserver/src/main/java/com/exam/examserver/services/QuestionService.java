package com.exam.examserver.services;

import java.util.Set;

import com.exam.examserver.entities.exam.Question;

public interface QuestionService {

	Question addQuestion(Question question);

	Question updateQuestion(Question question);

	Set<Question> getQuestions();

	Question getQuestion(String questionId);

	Set<Question> getQuestionOfQuiz(String quizId);

	void deleteQuestionById(String questionId);

	Question get(String questionId);
}
