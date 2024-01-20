package com.application.LibraryManagmentSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.LibraryManagmentSystem.Entity.Category;
import com.application.LibraryManagmentSystem.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAllCategory(){
		return categoryRepository.findAll();
	}
	
	public Category findCategoryById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category Not found"));
		return category;
	}
	
	public void createCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void deleteCategoryById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category Not found"));
		categoryRepository.deleteById(category.getId());
	}

}
