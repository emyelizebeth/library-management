package com.application.LibraryManagmentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.LibraryManagmentSystem.Entity.Publisher;
import com.application.LibraryManagmentSystem.service.PublisherService;

@Controller
public class PublisherController {

	@Autowired
	private PublisherService publisherService;
	
	@GetMapping("/publishers")
	public String findAllPublishers(Model model) {
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "publisherPage";
	}
	
	@GetMapping("/remove-publisher/{id}")
	public String deletePublisher(@PathVariable Long id, Model model) {
		publisherService.deletePublisherById(id);
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "publisherPage";
	}
	
	//to update publisher
	@GetMapping("/update-publisher/{id}")
	public String updatePublisher(@PathVariable Long id, Model model) {
		Publisher publisher = publisherService.findPublisherById(id);
		model.addAttribute("publishers", publisher);
		return "update-publisher";
	}
	
	@PostMapping("/save-update-publisher/{id}")
	public String saveUpdatePublisher(@PathVariable Long id, Publisher publisher, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "update-publisher";
		}
		publisherService.updatePublisher(publisher);
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}
	
	//to add new publisher
	@GetMapping("/add-publisher")
	public String addPublisher(Publisher publisher) {
		return "add-publisher";
	}
	
	@PostMapping("/save-publisher")
	public String savePublisher(Publisher publisher, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "add-publisher";
		}
		publisherService.createPublisher(publisher);
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}
}
