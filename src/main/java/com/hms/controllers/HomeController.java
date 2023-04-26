package com.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.annotation.PostConstruct;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String getHomePage() {
		return "home";
	}
}
