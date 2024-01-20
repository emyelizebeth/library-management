package com.application.LibraryManagmentSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.LibraryManagmentSystem.Entity.Books;
import com.application.LibraryManagmentSystem.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
	@Autowired
	private BookRepository bookRepository; 
	
	public List<Books> findAllBooks(){
		return bookRepository.findAll();
	}
	
	public Books findBookById(Long id) {
		Books book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
		return book;
	}
	
	public void createBook(Books book) {
		bookRepository.save(book);
	}
	
	public void updateBook(Books book) {
		bookRepository.save(book);
	}
	
	//to delete a book, first we've to find the book by id and and then delete it
	public void removeBook(Long id) {
		Books book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
		bookRepository.deleteById(book.getId());
	}

}
