package com.exam.examserver.services;

import java.util.List;
import java.util.Set;

import com.exam.examserver.entities.exam.Category;

public interface CategoryService {

	Category addCategory(Category category);

	Category updateCategory(Category category);

	Set<Category> getCategories();

	Category getCategoryById(String categoryId);

	void deleteCategory(String categoryId);
	List<Category> getAllCategories();
}
