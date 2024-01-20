package com.application.LibraryManagmentSystem.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "isbn", length = 50, nullable = false, unique = true)
	private Long isbn;
	
	@Column(name="book_name", length = 50, nullable = false)
	private String name;
	
	@Column(name="book_description", length = 250, nullable = false)
	private String description;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "books_authors",
	joinColumns = {@JoinColumn(name = "book_id")},
	inverseJoinColumns = {@JoinColumn(name = "author_id")})
	private Set<Author> authors_nonOwning = new HashSet<Author>(); 
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "books_categories",
	joinColumns = {@JoinColumn(name = "book_id")},
	inverseJoinColumns = {@JoinColumn(name = "category_id")})
	private Set<Category> category_nonOwning = new HashSet<Category>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "books_publishers",
	joinColumns = {@JoinColumn(name = "book_id")},
	inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
	private Set<Publisher> publisher_nonOwning = new HashSet<Publisher>();
 	
	 public void removePublisher(Publisher publisher) {
		 this.publisher_nonOwning.remove(publisher);//removing publisher from the set that we mentioned in line 56
		 publisher.getBooks().remove(publisher);// After publisher is removed from set, it has to be removed from book's as well.
		 }
	 
	 public void addPublisher(Publisher publisher) {
		 this.publisher_nonOwning.add(publisher);
		 publisher.getBooks().add(this);
	 }
	 
	 public void removeAuthor(Author author) {
		 this.authors_nonOwning.remove(author);
		 author.getBooks().remove(author);
		 }
	 
	 public void addAuthor(Author author) {
		 this.authors_nonOwning.add(author);
		 author.getBooks().add(this);
	 }
	
	 public void removeCategory(Category category) {
		 this.category_nonOwning.remove(category);
		 category.getBooks().remove(category);
		 }
	 
	 public void addCategory(Category category) {
		 this.category_nonOwning.add(category);
		 category.getBooks().add(this);
	 }

	public Books(Long isbn, String name, String description) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.description = description;
	}
	 
}
