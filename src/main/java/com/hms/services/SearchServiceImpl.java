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
		return new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(),
				room.getRoomDefinition().getRoomType(), room.getRoomDefinition().getMaxGuests(),
				room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
				room.getRoomDefinition().getImageUrl());
	}

	@Override
	public ArrayList<RoomDto> setRoomDtoAll() {
		ArrayList<RoomDto> roomDtoList = new ArrayList<RoomDto>();

		for (Room room : roomRepository.findAll()) {
			roomDtoList.add(new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(),
					room.getRoomDefinition().getRoomType(), room.getRoomDefinition().getMaxGuests(),
					room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
					room.getRoomDefinition().getImageUrl()));
		}
		return roomDtoList;
	}

	@Override
	public RoomDto setRoomDtoBasedOnAvailabityAndRoomCode(String roomCode, LocalDate checkIn, LocalDate checkOut) {
		Room room = roomRepository.findByRoomCodeBasedOnAvailabilityAndRoomCode(roomCode, checkIn, checkOut);
		if (room != null) {
			return new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(),
					room.getRoomDefinition().getRoomType(), room.getRoomDefinition().getMaxGuests(),
					room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
					room.getRoomDefinition().getImageUrl());
		} else {
			return new RoomDto();
		}

	}

	@Override
	public ArrayList<RoomDto> setRoomDtoBasedOnAvailabity(LocalDate checkIn, LocalDate checkOut) {
		ArrayList<RoomDto> roomDtoList = new ArrayList<RoomDto>();

		for (Room room : roomRepository.findByRoomCodeBasedOnAvailability(checkIn, checkOut)) {
			roomDtoList.add(new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(),
					room.getRoomDefinition().getRoomType(), room.getRoomDefinition().getMaxGuests(),
					room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
					room.getRoomDefinition().getImageUrl()));
		}
		return roomDtoList;
	}

	@Override
	public ArrayList<RoomDto> setRoomDtoBasedOnAvailabityAndBudget(Long budget, LocalDate checkIn, LocalDate checkOut) {
		ArrayList<RoomDto> roomDtoList = new ArrayList<RoomDto>();

		ArrayList<Long> range = decodeBudget(budget);

		for (Room room : roomRepository.findByRoomCodeBasedOnAvailabilityAndBudget(range.get(0), range.get(1), checkIn,
				checkOut)) {
			roomDtoList.add(new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(),
					room.getRoomDefinition().getRoomType(), room.getRoomDefinition().getMaxGuests(),
					room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
					room.getRoomDefinition().getImageUrl()));
		}

		return roomDtoList;
	}

	@Override
	public RoomDto setRoomDtoBasedOnAvailabityAndRoomCodeAndBudget(String roomCode, Long budget, LocalDate checkIn,
			LocalDate checkOut) {

		ArrayList<Long> range = decodeBudget(budget);

		Room room = roomRepository.findByRoomCodeBasedOnAvailabilityAndRoomCodeAndBudget(roomCode, range.get(0),
				range.get(1), checkIn, checkOut);
		if (room != null) {
			return new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(),
					room.getRoomDefinition().getRoomType(), room.getRoomDefinition().getMaxGuests(),
					room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
					room.getRoomDefinition().getImageUrl());
		} else {
			return new RoomDto();
		}

	}

	public ArrayList<Long> decodeBudget(Long budget) {
		ArrayList<Long> range = new ArrayList<Long>();
		switch (budget.intValue()) {
		case 1:
			range.add(0, (long) 0);
			range.add(1, (long) 3000);
			break;
		case 2:
			range.add(0, (long) 3001);
			range.add(1, (long) 5000);
			break;
		case 3:
			range.add(0, (long) 5001);
			range.add(1, (long) 9000);
			break;
		case 4:
			range.add(0, (long) 9001);
			range.add(1, (long) 15000);
			break;
		case 5:
			range.add(0, (long) 15001);
			range.add(1, (long) 300000);
			break;
		}
		return range;

	}

}
