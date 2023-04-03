package com.hms.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.hms.dto.BookingDto;
import com.hms.dto.RoomDto;
import com.hms.entities.Room;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface BookingService {
	
	Room getRoomByRoomNumber(Long roomNumber); 
	
	RoomDto setRoomDto(Long roomNumber);
	
	void persistBooking(BookingDto bookingDto);
	
	Long calculateBill(Long price, LocalDate checkInDate, LocalDate checkOutDate);
	
	String getLoggedInUserName();

}
