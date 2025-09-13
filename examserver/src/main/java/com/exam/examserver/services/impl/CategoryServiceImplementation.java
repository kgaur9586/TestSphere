package com.exam.examserver.services.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.repositories.CategoryRepository;
import com.exam.examserver.services.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		return new LinkedHashSet<>(this.categoryRepository.findAll());
	}

	@Override
	public Category getCategoryById(String categoryId) {
		return this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found"));
	}

	@Override
	public void deleteCategory(String categoryId) {
		this.categoryRepository.deleteById(categoryId);
	}
	@Override
	public List<Category> getAllCategories() {
		return this.categoryRepository.findAll();
	}
}
