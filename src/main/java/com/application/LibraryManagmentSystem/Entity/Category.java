package com.application.LibraryManagmentSystem.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "category_name", length = 50, nullable = false, unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "category_nonOwning", cascade = CascadeType.ALL)
	private Set<Books> books = new HashSet<Books>();

	public Category(String name) {
		super();
		this.name = name;
	}
	
}
