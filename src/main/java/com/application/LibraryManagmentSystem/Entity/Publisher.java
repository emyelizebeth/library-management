package com.application.LibraryManagmentSystem.Entity;

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

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "publisher_name", length = 50, unique = true, nullable = false)
	private String name;
	 
	@ManyToMany(mappedBy = "publisher_nonOwning", cascade = CascadeType.ALL)
	private Set<Books> books = new HashSet<Books>();

	public Publisher(String name) {
		super();
		this.name = name;
	}
	
	
}
