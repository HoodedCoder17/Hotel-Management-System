package com.hms.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hms.dto.UserDto;
import com.hms.services.BookingService;
import com.hms.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminActivitiesController {

	@Autowired
	UserService userService;

	@Autowired
	BookingService bookingService;

	@GetMapping(path = "/manage")
	public String showManagePage() {
		return "manage";
	}

	@GetMapping(path = "/manage/users")
	public String showAllUsersInfoPage(Model model) {
		ArrayList<UserDto> userDtoList = userService.convertUserListToUserDtoList(userService.fetchAllUsers());
		for (UserDto userDto : userDtoList) {
			Long ValueOfBookings = bookingService.fetchBookingValueByUserId(userDto.getUserId());
			userDto.setValueOfBookings(ValueOfBookings == null ? 0 : ValueOfBookings);
			userDto.setCountOfBookings(bookingService.fetchAmountOfBookingsByUserId(userDto.getUserId()));
		}
		model.addAttribute("userDtoList", userDtoList);
		return "users";
	}

	@GetMapping(path = "/manage/users/inactivateUser")
	public String showUserInactivatedPage(@RequestParam("userId") Long userId, Model model) {
		model.addAttribute("userId", userId);
		userService.inactivateUser(userId);
		return "userInactivated";
	}
	
	@GetMapping(path = "/manage/users/activateUser")
	public String showUserActivatedPage(@RequestParam("userId") Long userId, Model model) {
		model.addAttribute("userId", userId);
		userService.activateUser(userId);
		return "userActivated";
	}
	
	@GetMapping(path = "/manage/bookings")
	public String showAllBookings(Model model) {
		model.addAttribute("BookingDtoList", bookingService.findAllBookingsOfAllUsers());
		return "showAllBookings";
	}
	
	@GetMapping("/manage/bookings/cancelled")
	public String cancelBooking(@RequestParam("bookingId") Long bookingId, Model model, HttpSession session) {
		if (bookingService.cancelBooking(bookingId)) {
			model.addAttribute("bookingId", bookingId);
			return "cancelled";
		} else {
			model.addAttribute("error", true);
			model.addAttribute("BookingDtoList", bookingService.findAllBookingsOfAllUsers());
			session.setAttribute("errormsg", "Please make sure you are only cancelling the Bookings of the future!");
			return "showAllBookings";
		}
	}
	

}
