package com.application.LibraryManagmentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.LibraryManagmentSystem.Entity.Author;
import com.application.LibraryManagmentSystem.service.AuthorService;

@Controller
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/authors")
	public String findAllAuthors(Model model) {
		model.addAttribute("authors", authorService.findAllAuthors());
		return "authorPage";
	}
	
	//to remove author
	@GetMapping("/remove-author/{id}")
	public String removeAuthor(@PathVariable Long id, Model model) {
		authorService.deleteAuthorById(id);
		model.addAttribute("authors", authorService.findAllAuthors());
		return "authorPage";
	}
	
	//updating author
	@GetMapping("/update-author/{id}")
	public String updateAuthor(@PathVariable Long id, Model model) {
		Author author = authorService.findAuthorById(id);
		model.addAttribute("author", author);
		return "update-author";
	}
	
	@PostMapping("/save-update-author/{id}")
	public String saveUpdateAuthor(@PathVariable Long id, Author author, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "update-author";
		}
		authorService.updateAuthor(author);
		model.addAttribute("authors", authorService.findAllAuthors());
		return "redirect:/authors";
	
	}
	
	//to add authors
	@GetMapping("/add-author")
	public String addAuthor(Author author) {
		return "add-author";
	}
	
	@PostMapping("/save-author")
	public String saveAuthor(Author author, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "add-author";
		}
		authorService.createAuthor(author);
		model.addAttribute("authors", authorService.findAllAuthors());
		return "redirect:/authors";
	}
}
