package com.shopzone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopzone.entity.Category;
import com.shopzone.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	// Add records
	public Category addCategory(Category category) {
		return categoryRepo.save(category);
	}

	// to get all categories
	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	// to get Category based on ID
	public Category getcategoryById(Long id) {
		return categoryRepo.findById(id).orElse(null);
	}

	// to update category
	public Category updateCategory(Long id, Category category) {
		Category existingCategory = categoryRepo.findById(id).orElse(null);

		if (existingCategory == null) {
			return null;
		}

		existingCategory.setName(category.getName());
		return categoryRepo.save(existingCategory);
	}

	// to delete category
	public boolean deleteCategory(Long id) {
		if (!categoryRepo.existsById(id)) {
			return false;
		}
		categoryRepo.deleteById(id);
		return true;

	}

}
