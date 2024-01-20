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
@Table(name = "authors")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "description", length = 250, nullable = false)
	private String description;
	
	@Column(name = "author_name", length = 100, nullable = false, unique = true)
	private String name;
	
	//AUthors and books are having many-to-many relationship. Refer to 'RoadMap to FAANG.docx'
	@ManyToMany(mappedBy  = "authors_nonOwning", cascade = CascadeType.ALL)
	private Set<Books> books = new HashSet<Books>();

	public Author(String name, String description) {
		super();
		this.description = description;
		this.name = name;
	}

}
