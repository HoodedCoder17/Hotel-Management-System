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

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@GetMapping("/book")
	public String showBookingPage(@RequestParam("roomNumber") Long roomNumber, Model model) {
		RoomDto roomDto = bookingService.setRoomDto(roomNumber);
		model.addAttribute("roomDto", roomDto);
		model.addAttribute("bookingDto", new BookingDto(roomNumber));
		return "book";
	}

	@PostMapping("/book")
	public String ConfirmBooking(@ModelAttribute("bookingDto") BookingDto bookingDto, HttpSession session,
			Model model) {
		RoomDto roomDto = bookingService.setRoomDto(bookingDto.getRoomNumber());
		model.addAttribute("roomDto", roomDto);
		model.addAttribute("error", true);
		Boolean roomAvailability = bookingService.checkRoomAvailability(bookingDto.getRoomNumber(),
				bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
		Boolean GuestsWithinRoomLimit = bookingService.checkIfGuestsUnderTheRoomLimits(bookingDto.getNoOfGuests(),
				bookingDto.getRoomNumber());
		Boolean checkInDateIsInFuture = bookingService.checkIfCheckInDateIsInFuture(bookingDto.getCheckInDate());
		Boolean checkOutDateIsGreaterThanCheckInDate = bookingService
				.checkIfCheckOutDateIsGreaterThanCheckInDate(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
		if (roomAvailability && GuestsWithinRoomLimit && checkInDateIsInFuture
				&& checkOutDateIsGreaterThanCheckInDate) {
			bookingService.persistBooking(bookingDto);
			model.addAttribute("error", false);
			model.addAttribute("bookingDto", bookingDto);
			return "bookingConfirmation";
		} else if (!roomAvailability) {
			session.setAttribute("errormsg",
					"Sorry the room is booked during the given period, please choose some other period!");
			return "book";
		} else if (!GuestsWithinRoomLimit) {
			session.setAttribute("errormsg",
					"Please make sure the number of guests is not more than the holding capacity of the room!");
			return "book";
		} else if (!checkInDateIsInFuture) {
			session.setAttribute("errormsg", "Please make sure that the Check In Date is of the future!");
			return "book";
		} else {
			session.setAttribute("errormsg", "Please make sure that the Check Out Date is after Check In Date!");
			return "book";
		}

	}

	@GetMapping(path = "/showBookings")
	public String showUsersBookings(Model model) {
		model.addAttribute("BookingDtoList", bookingService.findAllBookingsOfLoggedInUser());
		return "showBookings";
	}

	@GetMapping("/cancelled")
	public String cancelBooking(@RequestParam("bookingId") Long bookingId, Model model, HttpSession session) {
		if (bookingService.cancelBooking(bookingId)) {
			model.addAttribute("bookingId", bookingId);
			return "cancelled";
		} else {
			model.addAttribute("error", true);
			model.addAttribute("BookingDtoList", bookingService.findAllBookingsOfLoggedInUser());
			session.setAttribute("errormsg", "Please make sure you are only cancelling the Bookings of the future!");
			return "showBookings";
		}
	}

}