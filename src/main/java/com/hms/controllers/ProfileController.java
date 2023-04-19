package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hms.dto.UserDto;
import com.hms.entities.User;
import com.hms.services.BookingService;
import com.hms.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

	@Autowired
	UserService userService;

	@Autowired
	BookingService bookingService;

	@GetMapping("/profile")
	public String showProfilePage(HttpServletRequest request, Model model) {
		String currentUserName = userService.getLoggedInUserName();
		UserDto userDto = new UserDto();
		User loggedInUser = userService.fetchUserByUserName(currentUserName);
		userDto.setUserName(loggedInUser.getUserName());
		userDto.setFirstName(loggedInUser.getFirstName());
		userDto.setLastName(loggedInUser.getLastName());
		userDto.setEmail(loggedInUser.getEmailId());
		userDto.setDateOfBirth(loggedInUser.getDateOfBirth());
		userDto.setRole(loggedInUser.getRole().toString());
		model.addAttribute("userDto", userDto);
		return "profile";
	}

}
