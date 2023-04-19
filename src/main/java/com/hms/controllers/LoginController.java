package com.hms.controllers;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hms.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@GetMapping("/signin")
	public String showLoginForm(HttpServletRequest request, HttpSession session, Model model) {
		model.addAttribute("user", new UserDto());
		session.setAttribute("errormsg", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		return "signin";
	}

	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		return error;
	}

	@GetMapping("/about")
	public String showAboutPage(HttpServletRequest request, HttpSession session, Model model) {
		return "about";
	}

	@GetMapping("/about/terms-of-use")
	public String showTermsOfUsePage(HttpServletRequest request, HttpSession session, Model model) {
		return "terms-of-use";
	}

	@GetMapping("/about/privacy")
	public String showPrivacyPage(HttpServletRequest request, HttpSession session, Model model) {
		return "privacy";
	}
}
