package com.shopzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopzone.entity.Category;
import com.shopzone.service.CategoryService;
import com.shopzone.service.UserService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private final UserService userService;

	@Autowired
	private CategoryService categoryService;

	CategoryController(UserService userService) {
		this.userService = userService;
	}

	// Add records
	@PostMapping
	public Category addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}

	// to get all categories
	@GetMapping
	public List<Category> getAllCategory() {
		return categoryService.getAllCategory();
	}

	// to get Category based on id
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable Long id, @RequestBody Category category) {
		return categoryService.updateCategory(id, category);
	}

	// to update category
	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
		return categoryService.updateCategory(id, category);
	}

	// to delete category
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable Long id) {
		boolean deleted = categoryService.deleteCategory(id);
		if (deleted) {
			return "Category deleted Successfully";
		}
		return "Category not deleted ";
	}
}
