package com.exam.examserver.entities.exam;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="quiz")
@Data
@NoArgsConstructor
public class Quiz {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long qid;
	private String title;
	
	
	private String description;
	
	private String maxMarks;
	
	private String numberOfQuestions;
	
	private boolean active = false;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	
	@OneToMany(mappedBy = "quiz",fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private Set<Question> questions = new HashSet<Question>();
	
	
	
}
