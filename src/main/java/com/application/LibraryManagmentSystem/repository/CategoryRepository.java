package com.application.LibraryManagmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.LibraryManagmentSystem.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
