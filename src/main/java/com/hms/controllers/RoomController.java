package com.hms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.entities.Room;
import com.hms.repositories.RoomRepository;

import jakarta.annotation.PostConstruct;

@RestController
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepository;

	/*
	 * @Autowired public RoomController(RoomRepository roomRepository) {
	 * this.roomRepository = roomRepository; }
	 */
	@PostConstruct
	public void postConstruct() {
		System.out.println("RoomController bean has been constructed");
	}

	@GetMapping(path = "/rooms", produces = "application/json")
	public List<Room> getAllRooms() {
		System.out.println("In /rooms page mapper");
		return roomRepository.findAll();
	}

}
