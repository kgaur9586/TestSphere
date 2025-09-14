package com.exam.examserver.entities.exam;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Category document stored in MongoDB.
 */
@Document(collection = "categories") // <— MongoDB collection name
@NoArgsConstructor
@Data
public class Category {

	@Id
	private String id; // <— MongoDB uses String as id by default

	private String title;
	private String description;

	/**
	 * For now we’ll store quiz IDs instead of JPA relationship.
	 * Later we can replace with @DBRef or embed quizzes.
	 */
	@JsonIgnore
	private Set<String> quizIds = new LinkedHashSet<>();
}
