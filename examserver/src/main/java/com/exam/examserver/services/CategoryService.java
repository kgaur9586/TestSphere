package com.exam.examserver.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.entities.exam.Category;


public interface CategoryService {
	
	public Category addCategory(Category category);
	public Category updateCategory(Category category);
	public Set<Category> getCategories();
	public Category getCategoryById(Long categoryId);
	
	public void deleteCategory(Long categoryId);
	

}
