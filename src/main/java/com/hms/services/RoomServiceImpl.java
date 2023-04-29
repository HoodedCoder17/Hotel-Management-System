package com.hms.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hms.dto.RoomDto;
import com.hms.entities.Maintainence;
import com.hms.entities.Room;
import com.hms.entities.RoomDefinition;
import com.hms.repositories.MaintainenceRepository;
import com.hms.repositories.RoomDefinitionRepository;
import com.hms.repositories.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomDefinitionRepository roomDefinitionRepository;

	@Autowired
	MaintainenceRepository maintainenceRepository;

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
	public void maintainRoom(Long roomNumber, LocalDate maintainenceStartDate, LocalDate maintainenceEndDate) {
		Room room = roomRepository.findByRoomNumber(roomNumber);
		Maintainence maintainence = new Maintainence(maintainenceStartDate, maintainenceEndDate, room);
		ArrayList<Maintainence> maintainence1 = new ArrayList<Maintainence>();
		maintainence1.add(maintainence);
		room.setMaintainence(maintainence1);
		room.setStatus("M");
		roomRepository.saveAndFlush(room);
		return;
	}

	@Override
	@Scheduled(fixedDelayString = "${ONE_DAY}")
	public void clearRoomsUnderMaintainence() {
		LocalDate now = LocalDate.now();
		ArrayList<Room> roomsUnderMaintainence = roomRepository.findRoomsWithStatusM();
		for (Room room : roomsUnderMaintainence) {
			if (room.getMaintainence().size() >= 1 && room.getMaintainence().get(room.getMaintainence().size() - 1).getMaintainenceEndDate().isBefore(now)) {
				room.setStatus("F");
				roomRepository.saveAndFlush(room);
			}
		}
		return;
	}

	@Override
	public String changePrice(String roomCode, Long newPrice) {
		RoomDefinition roomDefinition = roomDefinitionRepository.findByRoomCode(roomCode);
		roomDefinition.setprice(newPrice);
		roomDefinitionRepository.saveAndFlush(roomDefinition);
		return roomDefinition.getRoomType();
	}

}
