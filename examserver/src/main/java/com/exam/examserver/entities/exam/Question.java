package com.exam.examserver.entities.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="question")
@Data
@NoArgsConstructor
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long quesId;
	
	@Column(length = 5000)
	private String content;
	
	private String image;
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	private String answer;
	@Transient
	private String selectedAnswer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
	
}	
