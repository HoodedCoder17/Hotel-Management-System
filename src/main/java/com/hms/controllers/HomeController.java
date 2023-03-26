package com.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.annotation.PostConstruct;

@Controller
public class HomeController {

	@PostConstruct
	public void postConstruct() {
		System.out.println("HomeController bean has been constructed");
	}
	
	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("In home page mapper");
		return "home";
	}
}
