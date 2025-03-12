package com.exam.examserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.entities.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	public List<Quiz> findBycategory(Category category);
	
	public List<Quiz> findByactive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category c, Boolean b);

}
