package com.hms.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hms.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query(/* name = "findByRoomCode", */value = "SELECT R FROM Room R join RoomDefinition RD ON R.roomDefinition = RD.roomCode WHERE RD.roomCode = ?1")
	Room findByRoomCode(String roomCode);

	Room findByRoomId(String roomId);

	Room findByRoomNumber(Long roomNumber);

	@Query("SELECT R FROM Room R WHERE R.roomId NOT IN " + "(SELECT BD.room.roomId FROM BookingDetails BD "
			+ "WHERE (BD.checkInDate < ?2 AND BD.checkOutDate > ?1) "
			+ "OR (BD.checkInDate <= ?1 AND BD.checkOutDate >= ?2))")
	ArrayList<Room> findByRoomCodeBasedOnAvailability(LocalDate checkIn, LocalDate checkOut);

	@Query(value = "SELECT R FROM Room R join RoomDefinition RD ON R.roomDefinition = RD.roomCode WHERE RD.roomCode = ?1 "
			+ "AND R.roomId NOT IN (SELECT BD.room.roomId FROM BookingDetails BD WHERE"
			+ " (BD.checkInDate < ?3 AND BD.checkOutDate > ?2 ) OR (BD.checkInDate <= ?2 AND BD.checkOutDate >= ?3 ))")
	Room findByRoomCodeBasedOnAvailabilityAndRoomCode(String roomCode, LocalDate checkIn, LocalDate checkOut);

}