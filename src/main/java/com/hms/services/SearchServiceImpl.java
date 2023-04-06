package com.hms.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dto.RoomDto;
import com.hms.entities.Room;
import com.hms.entities.RoomDefinition;
import com.hms.repositories.RoomDefinitionRepository;
import com.hms.repositories.RoomRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	RoomDefinitionRepository roomDefinitionRepository;

	@Autowired
	RoomRepository roomRepository;

	public ArrayList<RoomDefinition> fetchRoomDefinitions() {

		return (ArrayList<RoomDefinition>) roomDefinitionRepository.findAll();
	}

	@Override
	public RoomDefinition fetchRoomDefinitionByCode(String roomCode) {

		return roomDefinitionRepository.findByRoomCode(roomCode);
	}

	@Override
	public Room fetchRoomByRoomCode(String roomCode) {

		return roomRepository.findByRoomCode(roomCode);
	}

	@Override
	public Room fetchRoomById(String roomId) {

		return roomRepository.findByRoomId(roomId);
	}

	@Override
	public RoomDto setRoomDto(String roomCode) {
		Room room = fetchRoomByRoomCode(roomCode);
		return new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(), room.getRoomDefinition().getRoomType(),
				room.getRoomDefinition().getMaxGuests(), room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
				room.getRoomDefinition().getImageUrl());
	}

	@Override
	public ArrayList<RoomDto> setRoomDtoAll() {
		ArrayList<RoomDto> roomDtoList = new ArrayList<RoomDto>();

		for (Room room : roomRepository.findAll()) {
			roomDtoList.add(new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(), room.getRoomDefinition().getRoomType(),
					room.getRoomDefinition().getMaxGuests(), room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
					room.getRoomDefinition().getImageUrl()));
		}
		return roomDtoList;
	}
	
	@Override
	public RoomDto setRoomDtoBasedOnAvailabityAndRoomCode(String roomCode, LocalDate checkIn, LocalDate checkOut) {
		Room room = roomRepository.findByRoomCodeBasedOnAvailabilityAndRoomCode(roomCode,checkIn,checkOut);
		if (room != null) {
			return new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(), room.getRoomDefinition().getRoomType(),
					room.getRoomDefinition().getMaxGuests(), room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
					room.getRoomDefinition().getImageUrl());
		} else {
			return new RoomDto();
		}
		
	}

	@Override
	public ArrayList<RoomDto> setRoomDtoBasedOnAvailabity(LocalDate checkIn, LocalDate checkOut) {
		ArrayList<RoomDto> roomDtoList = new ArrayList<RoomDto>();

		for (Room room : roomRepository.findByRoomCodeBasedOnAvailability(checkIn, checkOut)) {
			roomDtoList.add(new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(), room.getRoomDefinition().getRoomType(),
					room.getRoomDefinition().getMaxGuests(), room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
					room.getRoomDefinition().getImageUrl()));
		}
		return roomDtoList;
	}
	
	

}
