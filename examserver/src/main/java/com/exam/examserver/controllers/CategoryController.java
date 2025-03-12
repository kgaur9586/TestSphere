package com.exam.examserver.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.entities.exam.Category;
import com.exam.examserver.services.CategoryService;
import com.exam.examserver.services.impl.CategoryServiceImplementation;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//add category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		Category category1 = this.categoryService.addCategory(category);
		return ResponseEntity.ok(category1);
	}	
	
	// get category
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId){
		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}
	
	//get all categories
	@GetMapping("/")
	public ResponseEntity<?> getAllCategories(){
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	//update category
	@PutMapping("/")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		return ResponseEntity.ok(this.categoryService.updateCategory(category));
	}
	
	//delete category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable("categoryId") Long categoryId) {
	    this.categoryService.deleteCategory(categoryId);
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Category deleted successfully");
	    return ResponseEntity.ok(response);
	}
}
