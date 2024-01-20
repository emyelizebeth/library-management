package com.application.LibraryManagmentSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.LibraryManagmentSystem.Entity.Category;
import com.application.LibraryManagmentSystem.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public String findAllCategories(Model model){
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		return "categoryPage";
	}
	
	//to delete a category
	@GetMapping("/remove-category/{id}")
	public String deleteCategory(@PathVariable Long id, Model model) {
		categoryService.deleteCategoryById(id);
		model.addAttribute("categories", categoryService.findAllCategory());
		return "categoryPage";
	}
	
	//to update category, first get the category by id and then a new screen will be displayed to edit and save it
	@GetMapping("/update-category/{id}")
	public String updateCategory(@PathVariable Long id, Model model) {
		Category category = categoryService.findCategoryById(id);
		model.addAttribute("categories", category);
		return "update-category"; // this is the new page with editable fields and 'save' button
	}
	
	@PostMapping("/save-update-category/{id}")
	public String saveCategory(@PathVariable Long id, Category category, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "update-category";
		}
		categoryService.updateCategory(category);
		model.addAttribute("categories", categoryService.findAllCategory());
		return "redirect:/categories";
		
	}
	
	//to add category
	@GetMapping("/add-category")
	public String addCategory(Category category) {
		return "add-category";
	}
	
	@PostMapping("/save-category")
	public String saveCategory(Category category,  BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-category";
		}
		categoryService.createCategory(category);
		model.addAttribute("categories", categoryService.findAllCategory());
		return "redirect:/categories";
	}
}
