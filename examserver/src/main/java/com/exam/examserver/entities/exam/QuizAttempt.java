package com.exam.examserver.entities.exam;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "quiz_attempts")
@Data
@NoArgsConstructor
public class QuizAttempt {

	@Id
	private String id; // MongoDB ObjectId

	private String quizId; // store quiz ID instead of Quiz object
	private String userId; // store user ID instead of User object

	private LocalDateTime attemptTime;
}
