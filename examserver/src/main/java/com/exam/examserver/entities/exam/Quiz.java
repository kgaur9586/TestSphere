package com.exam.examserver.entities.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "quizzes") // Mongo collection
@Data
@NoArgsConstructor
public class Quiz {

	@Id
	private String id; // MongoDB’s primary key

	private String title;
	private String description;
	private String maxMarks;
	private String numberOfQuestions;
	private boolean active = false;

	// Instead of ManyToOne with Category entity, store categoryId
	private String categoryId;

	// Instead of OneToMany to Questions, store question IDs for now
	@JsonIgnore
	private Set<String> questionIds = new HashSet<>();
}
