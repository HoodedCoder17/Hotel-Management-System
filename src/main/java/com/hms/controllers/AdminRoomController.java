package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hms.dto.BookingDto;
import com.hms.entities.Room;
import com.hms.services.RoomService;
import com.hms.services.SearchService;

@Controller
public class AdminRoomController {

	@Autowired
	SearchService searchService;

	@Autowired
	RoomService roomService;

	@GetMapping(path = "manage/rooms/addRoom")
	public String showAddRoom(Model model) {
		model.addAttribute("roomDtoList", roomService.findAllRoomDefinitions());
		return "addRoom";
	}

	@GetMapping(path = "manage/rooms/roomAdded")
	public String showRoomAddedPage(@RequestParam("roomCode") String roomCode, Model model) {
		Room room = roomService.addRoomByRoomCode(roomCode);
		model.addAttribute("roomNumber", room.getRoomNumber());
		return "roomAdded";
	}

	@GetMapping(path = "manage/rooms/decommissionRoom")
	public String showDecommissionRoomPage(Model model) {
		model.addAttribute("roomDto", searchService.setRoomDtoAll());
		return "decommissionRoom";
	}

	@GetMapping(path = "manage/rooms/roomDecommissioned")
	public String showRoomDecommissionedPage(@RequestParam("roomNumber") Long roomNumber, Model model) {
		if (roomService.decommissionRoomByRoomCode(roomNumber)) {
			model.addAttribute("roomNumber", roomNumber);
			return "roomDecommissioned";
		} else {
			model.addAttribute("roomDto", searchService.setRoomDtoAll());
			return "decommissionRoom";
		}

	}

	@GetMapping(path = "manage/rooms/maintainRoom")
	public String showMaintainRoomPage(Model model) {
		model.addAttribute("roomDto", searchService.setRoomDtoAll());
		return "maintainRoom";
	}

	@GetMapping(path = "manage/rooms/maintainRoom/selectDates")
	public String showSelectDatesPage(@RequestParam("roomNumber") Long roomNumber, Model model) {
		BookingDto bookingDto = new BookingDto();
		bookingDto.setRoomNumber(roomNumber);
		model.addAttribute("bookingDto", bookingDto);
		return "selectDates";
	}

	@PostMapping(path = "manage/rooms/maintainRoom/roomUnderMaintainence")
	public String showRoomUnderMaintainencePage(@ModelAttribute("bookingDto") BookingDto bookingDto, Model model) {
		roomService.maintainRoom(bookingDto.getRoomNumber(), bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
		model.addAttribute("roomNumber", bookingDto.getRoomNumber());
		model.addAttribute("maintainenceStartDate", bookingDto.getCheckInDate());
		model.addAttribute("maintainenceEndDate", bookingDto.getCheckOutDate());
		return "roomUnderMaintainence";
	}
}
