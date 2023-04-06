package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hms.dto.BookingDto;
import com.hms.dto.RoomDto;
import com.hms.services.BookingService;

@Controller
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	BookingService bookingService;

	@GetMapping("/book")
	public String showBookingPage(@RequestParam("roomNumber") Long roomNumber, Model model) {
		System.out.println(roomNumber);
		RoomDto roomDto = bookingService.setRoomDto(roomNumber);
		model.addAttribute("roomDto", roomDto);
		model.addAttribute("bookingDto", new BookingDto(roomNumber));
	    return "book";
	}
	
	@PostMapping("/book")
	public String ConfirmBooking(@ModelAttribute("bookingDto") BookingDto bookingDto) {
		bookingService.persistBooking(bookingDto);
		return "home";
		
	}

}
