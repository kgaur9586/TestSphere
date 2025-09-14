package com.exam.examserver.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.repositories.CategoryRepository;
import com.exam.examserver.repositories.QuizRepository;
import com.exam.examserver.responseDto.ActiveQuizResponse;
import com.exam.examserver.responseDto.QuizResponse;
import com.exam.examserver.services.CategoryService;
import com.exam.examserver.services.QuizService;

@Service
public class QuizServiceImplementation implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getAllQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(String quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        return quiz;
    }

    @Override
    public QuizResponse getQuizWithCategory(String quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz == null) {
            return null; // or throw an exception
        }
        List<Category> categories = categoryRepository.findAll();
        return new QuizResponse(quiz, categories);
    }

    @Override
    public void deleteQuiz(String quizId) {
        this.quizRepository.deleteById(quizId);
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(String categoryId) {
        return this.quizRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<ActiveQuizResponse> getActiveQuizzes() {
        List<Quiz> activeQuizzes = this.quizRepository.findByActive(true);

        return activeQuizzes.stream()
                .map(quiz -> new ActiveQuizResponse(quiz,
                        this.categoryRepository.findById(quiz.getCategoryId()).orElse(null)))
                .toList();

    }

    @Override
    public List<ActiveQuizResponse> getActiveQuizzesOfCategory(String categoryId) {
        List<Quiz> activeQuizzes = this.quizRepository.findByCategoryIdAndActive(categoryId, true);
        return activeQuizzes.stream()
                .map(quiz -> new ActiveQuizResponse(quiz,
                        this.categoryRepository.findById(quiz.getCategoryId()).orElse(null)))
                .toList();
    }
}
