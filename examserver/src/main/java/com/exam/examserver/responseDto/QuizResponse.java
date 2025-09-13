// QuizResponse.java
package com.exam.examserver.responseDto;

import com.exam.examserver.entities.exam.Quiz;

import java.util.List;

import com.exam.examserver.entities.exam.Category;
import lombok.Data;

@Data
public class QuizResponse {
    private String id;
    private String title;
    private String description;
    private String maxMarks;
    private String numberOfQuestions;
    private boolean active;
    private List<Category> categories; // full category object

    public QuizResponse(Quiz quiz, List<Category> categories) {
        this.categories = categories;
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.description = quiz.getDescription();
        this.maxMarks = quiz.getMaxMarks();
        this.numberOfQuestions = quiz.getNumberOfQuestions();
        this.active = quiz.isActive();
    }
}
