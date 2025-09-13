package com.exam.examserver.entities.exam;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "questions")
@Data
@NoArgsConstructor
public class Question {

	@Id
	private String id; // MongoDB ID instead of long quesId

	private String content;
	private String image;

	private String option1;
	private String option2;
	private String option3;
	private String option4;

	private String answer;
	private String selectedAnswer;

	// Instead of ManyToOne to Quiz, store quizId
	private String quizId;
}
