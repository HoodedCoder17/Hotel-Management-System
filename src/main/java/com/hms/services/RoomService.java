package com.hms.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.dto.RoomDto;
import com.hms.entities.Room;

@Service
@Transactional
public interface RoomService {

	Room addRoomByRoomCode(String roomCode);

	Boolean decommissionRoomByRoomCode(Long roomNumber);

	ArrayList<RoomDto> findAllRoomDefinitions();

	void maintainRoom(Long roomNumber, LocalDate maintainenceStartDate, LocalDate maintainenceEndDate);
	
	void clearRoomsUnderMaintainence();

	String changePrice(String roomCode, Long newPrice);

}