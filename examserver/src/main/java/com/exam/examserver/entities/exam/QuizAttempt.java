package com.exam.examserver.entities.exam;

import java.time.LocalDateTime;

import com.exam.examserver.entities.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="quiz_attempts")
@Data
@NoArgsConstructor
public class QuizAttempt {	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@ManyToOne
		@JoinColumn(name="quiz_id",nullable=false)
		private Quiz quiz;
		
		@ManyToOne
		@JoinColumn(name="user_id")
		private User user;
		
		private LocalDateTime attemptTime;
		
		
}
