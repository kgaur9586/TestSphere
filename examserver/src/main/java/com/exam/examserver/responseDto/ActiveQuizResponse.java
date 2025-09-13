package com.exam.examserver.responseDto;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.entities.exam.Quiz;

import lombok.Data;

@Data
public class ActiveQuizResponse {
    private String id;
    private String title;
    private String description;
    private String maxMarks;
    private String numberOfQuestions;
    private boolean active;
    private Category category;

    public ActiveQuizResponse(Quiz quiz, Category category) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.description = quiz.getDescription();
        this.maxMarks = quiz.getMaxMarks();
        this.numberOfQuestions = quiz.getNumberOfQuestions();
        this.active = quiz.isActive();
        this.category = category;
    }
}
