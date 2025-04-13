package com.exam.examserver.services.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.repositories.QuizRepository;
import com.exam.examserver.services.QuizService;

@Service
public class QuizServiceImplementation implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    @CachePut(value = "quizzes", key = "#quiz.qid")
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getAllQuizzes() {
        return new HashSet<Quiz>(this.quizRepository.findAll());
    }

    @Override
    @Cacheable(value = "quizzes", key = "#quizId")
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).orElse(null);
    }

    @Override
    public void deleteQuiz(Long quizId) {
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        this.quizRepository.delete(quiz);
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) {
        return this.quizRepository.findBycategory(category);
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByactive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category category) {
        return this.quizRepository.findByCategoryAndActive(category, true);
    }
}
