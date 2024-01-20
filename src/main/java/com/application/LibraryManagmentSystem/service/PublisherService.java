package com.application.LibraryManagmentSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.LibraryManagmentSystem.Entity.Publisher;
import com.application.LibraryManagmentSystem.repository.PublisherRepository;

@Service
public class PublisherService {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	public List<Publisher> findAllPublishers(){
		return publisherRepository.findAll();
	}
	
	public Publisher findPublisherById(Long id) {
		Publisher publisher = publisherRepository.findById(id).orElseThrow(()-> new RuntimeException("Publisher not found"));
		return publisher;
	}
	
	public void createPublisher(Publisher publisher) {
		publisherRepository.save(publisher);
	}
	
	public void updatePublisher(Publisher publisher) {
		publisherRepository.save(publisher);
	}
	
	public void deletePublisherById(Long id) {
		Publisher publisher = publisherRepository.findById(id).orElseThrow(()-> new RuntimeException("Publisher not found"));
		publisherRepository.deleteById(publisher.getId());
	}

}
