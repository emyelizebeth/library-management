package com.application.LibraryManagmentSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.LibraryManagmentSystem.Entity.Books;
import com.application.LibraryManagmentSystem.service.AuthorService;
import com.application.LibraryManagmentSystem.service.BookService;
import com.application.LibraryManagmentSystem.service.CategoryService;
import com.application.LibraryManagmentSystem.service.PublisherService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/books")
	public String findAllBooks(Model model) {
		List<Books> bookList = bookService.findAllBooks();
		model.addAttribute("books", bookList);
		return "bookPage"; // name of the html file which is going to render bookList
	}
	
	@GetMapping("/book/{id}")
	public String findBook(@PathVariable Long id, Model model) {
		Books book = bookService.findBookById(id);
		model.addAttribute("books", book);
		return "list-book";
	}
	
	@GetMapping("/remove-book/{id}")
	public String deleteBook(@PathVariable Long id, Model model) {
		bookService.removeBook(id);
		//after deleting book we've to refresh the ui and show all books with the book deleted
		model.addAttribute("books", bookService.findAllBooks());
		return "bookPage";
	}
	
	//how to update. when we edit, a new page will be shown in editable fields and the user may/maynot edit the fields and click on the save button which will redirect to main screen(displaying the books)
	//so there will be 1 html page needed (with save button) and 2 controllers (1. managing the click of edit button , 2. when the user hits save button)
	
	@GetMapping("/update-book/{id}")
		public String updateBook(@PathVariable Long id, Model model) {
			Books book = bookService.findBookById(id);
			model.addAttribute("books", book);
			//In the UI, we need to show all categories, all authors, all publishers in drop-down. this is bcoz if user is going to edit he is going to see the details in dropdown.
			model.addAttribute("categories", categoryService.findAllCategory());
			model.addAttribute("publishers", publisherService.findAllPublishers());
			model.addAttribute("authors", authorService.findAllAuthors());
			return "update-book";
		}
	
	@PostMapping("/save-update/{id}")
	public String saveUpdateBook(@PathVariable Long id, Books book, BindingResult result, Model model ) {
		if(result.hasErrors()) {
			return "update-book";
		}
		bookService.updateBook(book);
		model.addAttribute("books", bookService.findAllBooks());
		return "redirect:/books"; //it will hit /books endpoint from line 34
	}
	
	//to add book, we require 2 controllers
	@GetMapping("/add-book")
	public String addBook(Books book, Model model) {
		//In the UI, we need to show all categories, all authors, all publishers in drop-down. this is bcoz if user is going to edit he is going to see the details in dropdown.
		model.addAttribute("categories", categoryService.findAllCategory());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		model.addAttribute("authors", authorService.findAllAuthors());
		return "add-book";
	}
		
	@PostMapping("/save-book")
	public String saveUpdateBook(Books book, BindingResult result, Model model ) {
		if(result.hasErrors()) {
			return "add-book";
		}
		bookService.createBook(book);
		model.addAttribute("books", bookService.findAllBooks());
		return "redirect:/books"; //it will hit /books endpoint from line 34
	}
	
}
