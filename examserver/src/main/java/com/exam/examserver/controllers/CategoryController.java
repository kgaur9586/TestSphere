package com.exam.examserver.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// add category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category category1 = this.categoryService.addCategory(category);
		return ResponseEntity.ok(category1);
	}

	// get category by id
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable("categoryId") String categoryId) {
		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}

	// get all categories
	@GetMapping("/")
	public ResponseEntity<?> getAllCategories() {
		return ResponseEntity.ok(this.categoryService.getCategories());
	}

	// update category
	@PutMapping("/")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
		return ResponseEntity.ok(this.categoryService.updateCategory(category));
	}

	// delete category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable("categoryId") String categoryId) {
		this.categoryService.deleteCategory(categoryId);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Category deleted successfully");
		return ResponseEntity.ok(response);
	}
}
