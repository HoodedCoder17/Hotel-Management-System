package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hms.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query(name = "findByRoomCode", value = "SELECT R FROM Room R join RoomDefinition RD ON R.roomDefinition = RD.roomCode WHERE RD.roomCode = ?1")
	Room findByRoomCode(String roomCode);

	Room findByRoomId(String roomId);
	
	Room findByRoomNumber(Long roomNumber);

}