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
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomDefinitionRepository roomDefinitionRepository;

	@Override
	public Room addRoomByRoomCode(String roomCode) {
		return roomRepository.saveAndFlush(new Room(roomRepository.findMaxRoomNumberByRoomCode(roomCode) + 1,
				roomDefinitionRepository.findByRoomCode(roomCode), "F"));
	}

	@Override
	public Boolean decommissionRoomByRoomCode(Long roomNumber) {
		return roomRepository.decommissionRoomQuery(roomNumber) == 1 ? true : false;
	}

	@Override
	public ArrayList<RoomDto> findAllRoomDefinitions() {
		ArrayList<RoomDto> roomDtoList = new ArrayList<RoomDto>();
		for (RoomDefinition roomDefinition : roomDefinitionRepository.findAll()) {
			roomDtoList.add(new RoomDto(null, roomDefinition.getRoomCode(), roomDefinition.getRoomType(),
					roomDefinition.getMaxGuests(), roomDefinition.getPrice(), roomDefinition.getDescription(),
					roomDefinition.getImageUrl()));
		}
		return roomDtoList;
	}

	@Override
	public Boolean maintainRoom(Long roomNumber, LocalDate maintainenceStartDate, LocalDate maintainenceEndDate) {
		return roomRepository.maintainRoomQuery(roomNumber, maintainenceStartDate, maintainenceEndDate) == 1 ? true
				: false;
	}

}
