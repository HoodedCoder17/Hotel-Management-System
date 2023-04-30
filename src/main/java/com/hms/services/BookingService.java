package com.hms.services;

import java.time.LocalDate;
import java.util.ArrayList;

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
	
	Boolean checkRoomAvailability(Long roomNumber, LocalDate checkInDate, LocalDate checkOutDate);
	
	Boolean checkIfGuestsUnderTheRoomLimits(Long expectedGuests,Long roomNumber);
	
	Boolean checkIfCheckInDateIsInFuture(LocalDate checkInDate);
	
	Boolean checkIfCheckOutDateIsGreaterThanCheckInDate(LocalDate checkInDate, LocalDate checkOutDate);
	
	ArrayList<BookingDto> findAllBookingsOfLoggedInUser();
	
	Boolean cancelBooking(Long bookingId);
	
	Boolean checkIfBookingIsInFuture(LocalDate checkInDate);
	
	Long fetchAmountOfBookingsByUserId(Long userId);
	
	Long fetchBookingValueByUserId(Long userId);
	
	ArrayList<BookingDto> findAllBookingsOfAllUsers();

}
