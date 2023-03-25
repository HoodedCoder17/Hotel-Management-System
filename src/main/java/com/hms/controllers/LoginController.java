package com.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hms.dto.UserDto;
import com.hms.entities.User;
import com.hms.exceptions.UserAlreadyExistException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	
	@GetMapping("/signin")
	public String showLoginForm(WebRequest request, Model model) {
		model.addAttribute("user", new UserDto());
		return "signin";
	}
	
}
