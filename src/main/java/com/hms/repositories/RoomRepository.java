package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	
	Room findByRoomCode(String roomCode);
	
	Room findByRoomId(String roomId);

}