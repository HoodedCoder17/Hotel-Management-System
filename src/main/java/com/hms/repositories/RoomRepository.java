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

	@Query("SELECT R FROM Room R join RoomDefinition RD ON R.roomDefinition = RD.roomCode WHERE R.roomId NOT IN "
			+ "(SELECT BD.room.roomId FROM BookingDetails BD " + "WHERE (BD.checkInDate < ?2 AND BD.checkOutDate > ?1) "
			+ "OR (BD.checkInDate <= ?1 AND BD.checkOutDate >= ?2)) ORDER BY RD.price")
	ArrayList<Room> findByRoomCodeBasedOnAvailability(LocalDate checkIn, LocalDate checkOut);

	@Query(value = "SELECT R FROM Room R join RoomDefinition RD ON R.roomDefinition = RD.roomCode WHERE RD.roomCode = ?1 "
			+ "AND R.roomId NOT IN (SELECT BD.room.roomId FROM BookingDetails BD WHERE"
			+ " (BD.checkInDate < ?3 AND BD.checkOutDate > ?2 ) OR (BD.checkInDate <= ?2 AND BD.checkOutDate >= ?3 )) ORDER BY RD.price")
	Room findByRoomCodeBasedOnAvailabilityAndRoomCode(String roomCode, LocalDate checkIn, LocalDate checkOut);

	@Query(value = "SELECT R FROM Room R join RoomDefinition RD ON R.roomDefinition = RD.roomCode WHERE RD.price >= ?1 AND RD.price <= ?2 "
			+ "AND R.roomId NOT IN (SELECT BD.room.roomId FROM BookingDetails BD WHERE"
			+ " (BD.checkInDate < ?4 AND BD.checkOutDate > ?3 ) OR (BD.checkInDate <= ?3 AND BD.checkOutDate >= ?4 )) ORDER BY RD.price")
	ArrayList<Room> findByRoomCodeBasedOnAvailabilityAndBudget(Long budgetMin, Long budgetMax, LocalDate checkIn,
			LocalDate checkOut);

	@Query(value = "SELECT R FROM Room R join RoomDefinition RD ON R.roomDefinition = RD.roomCode WHERE RD.roomCode = ?1  AND RD.price >= ?2 AND RD.price <= ?3 "
			+ "AND R.roomId NOT IN (SELECT BD.room.roomId FROM BookingDetails BD WHERE"
			+ " (BD.checkInDate < ?5 AND BD.checkOutDate > ?4 ) OR (BD.checkInDate <= ?4 AND BD.checkOutDate >= ?5 )) ORDER BY RD.price")
	Room findByRoomCodeBasedOnAvailabilityAndRoomCodeAndBudget(String roomCode, Long budgetMin,
			Long budgetMax, LocalDate checkIn, LocalDate checkOut);

}