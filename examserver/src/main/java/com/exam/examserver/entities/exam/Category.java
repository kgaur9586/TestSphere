package com.exam.examserver.entities.exam;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="category")
@NoArgsConstructor
@Data
public class Category {
		@Id()
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long cid;
		
		private String title;
		private String description;
		
		@OneToMany(mappedBy = "category",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		@JsonIgnore
		private Set<Quiz> quizzes = new LinkedHashSet<>();
		
		
}
