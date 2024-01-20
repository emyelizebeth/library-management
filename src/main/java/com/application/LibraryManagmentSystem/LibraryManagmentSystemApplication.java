package com.application.LibraryManagmentSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.application.LibraryManagmentSystem.Entity.Author;
import com.application.LibraryManagmentSystem.Entity.Books;
import com.application.LibraryManagmentSystem.Entity.Category;
import com.application.LibraryManagmentSystem.Entity.Publisher;
import com.application.LibraryManagmentSystem.service.BookService;

@SpringBootApplication
public class LibraryManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagmentSystemApplication.class, args);
		
		
	}
	
	//will be executed when the appln is launched for the first time
			@Bean
			public CommandLineRunner initialCreate(BookService bookService) {
				return(args)->{
					//this dummy code will insert and create dummy data for us in db
					
					Books book1 = new Books(123L, "The Complete Software Developer's Career Guide", "Professional guide");
					Author author1 = new Author("John Sonmez", "Consultant");
					Category category1 = new Category("Professional Development");
					Publisher publisher1 = new Publisher("Simple Programmer, LLC");
					book1.addAuthor(author1);
					book1.addCategory(category1);
					book1.addPublisher(publisher1);
					bookService.createBook(book1);
					
					Books book2 = new Books(124L, "How to win friends and influence people", "Motivational Book");
					Author author2 = new Author("Dale Carnegie", "American Wrietr & Lecturer");
					Category category2 = new Category("Self Help Book");
					Publisher publisher2 = new Publisher("Simon & Schuster");
					book2.addAuthor(author2);
					book2.addCategory(category2);
					book2.addPublisher(publisher2);
					bookService.createBook(book2);
					
					Books book3 = new Books(125L, "Atomic Habits", "Motivational Book");
					Author author3 = new Author("James Clear", "American Wrietr");
					Category category3 = new Category("Personal Development & Psychology");
					Publisher publisher3 = new Publisher("Barnes & Noble");
					book3.addAuthor(author3);
					book3.addCategory(category3);
					book3.addPublisher(publisher3);
					bookService.createBook(book3);
					
					
					
				};
			}

}
