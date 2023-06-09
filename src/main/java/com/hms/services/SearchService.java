package com.hms.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.hms.dto.RoomDto;
import com.hms.entities.Room;
import com.hms.entities.RoomDefinition;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface SearchService {
	
	ArrayList<RoomDefinition> fetchRoomDefinitions();
	
	Room fetchRoomByRoomCode(String roomCode);
	
	Room fetchRoomById(String roomId);

	RoomDefinition fetchRoomDefinitionByCode(String roomCode);
	
	RoomDto setRoomDto(String roomCode);
	
	ArrayList<RoomDto> setRoomDtoAll();
	
	ArrayList<RoomDto> setRoomDtoBasedOnAvailabity(LocalDate checkIn, LocalDate checkOut);

	ArrayList<RoomDto> setRoomDtoBasedOnAvailabityAndRoomCode(String roomCode, LocalDate checkIn, LocalDate checkOut);
	
	ArrayList<RoomDto> setRoomDtoBasedOnAvailabityAndBudget(Long budget, LocalDate checkIn, LocalDate checkOut);
	
	ArrayList<RoomDto> setRoomDtoBasedOnAvailabityAndRoomCodeAndBudget(String roomCode, Long budget, LocalDate checkIn, LocalDate checkOut);
	
	ArrayList<Long> decodeBudget(Long budget);

}
