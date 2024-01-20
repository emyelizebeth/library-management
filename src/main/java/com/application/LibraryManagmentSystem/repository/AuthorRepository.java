package com.application.LibraryManagmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.LibraryManagmentSystem.Entity.Author;
import com.application.LibraryManagmentSystem.Entity.Books;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}


