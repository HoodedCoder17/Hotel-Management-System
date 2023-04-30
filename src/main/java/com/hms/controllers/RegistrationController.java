
package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "register";
	}

	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result,
			HttpServletRequest request, Errors errors, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		if (result.hasErrors()) {
			return "register";
		}

		try {
			User registered = userService.registerNewUserAccount(userDto);
		} catch (UserAlreadyExistException uaeEx) {
			mav.addObject("message", "An account for that username/email already exists.");
			session.setAttribute("errormsg", "Your Registration is complete, please login");
			return "signin";
		}

		return "signin";
	}
}
