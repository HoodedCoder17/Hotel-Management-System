
package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hms.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		System.out.println("In Register getMapping");
		return "register";
	}

	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, HttpServletRequest request,
			Errors errors) {

		ModelAndView mav = new ModelAndView();
		
		System.out.println("In Register postMapping"+userDto.toString());

		try {
			User registered = userService.registerNewUserAccount(userDto);
		} catch (UserAlreadyExistException uaeEx) {
			mav.addObject("message", "An account for that username/email already exists.");
			return "/signin";
		}

		return "/signin";
	}
}
