package com.hms.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hms.dto.BookingDto;
import com.hms.dto.RoomDto;
import com.hms.entities.BillingDetails;
import com.hms.entities.BookingDetails;
import com.hms.entities.Room;
import com.hms.repositories.BillingDetailsRepository;
import com.hms.repositories.BookingDetailsRepository;
import com.hms.repositories.RoomDefinitionRepository;
import com.hms.repositories.RoomRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	RoomDefinitionRepository roomDefinitionRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	BookingDetailsRepository bookingDetailsRepository;

	@Autowired
	BillingDetailsRepository BillingDetailsRepository;

	@Autowired
	UserService userService;

	@Override
	public Room getRoomByRoomNumber(Long roomNumber) {

		return roomRepository.findByRoomNumber(roomNumber);
	}

	@Override
	public RoomDto setRoomDto(Long roomNumber) {
		Room room = getRoomByRoomNumber(roomNumber);
		return new RoomDto(room.getRoomNumber(), room.getRoomDefinition().getRoomCode(),
				room.getRoomDefinition().getRoomType(), room.getRoomDefinition().getMaxGuests(),
				room.getRoomDefinition().getPrice(), room.getRoomDefinition().getDescription(),
				room.getRoomDefinition().getImageUrl());
	}

	@Override
	public void persistBooking(BookingDto bookingDto) {
		LocalDate now = LocalDate.now();
		String currentUserName = getLoggedInUserName();
		RoomDto roomDto = setRoomDto(bookingDto.getRoomNumber());
		Room room = roomRepository.findByRoomNumber(roomDto.getRoomNumber());
		BookingDetails bookingDetails = new BookingDetails(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate(),
				bookingDto.getNoOfGuests(), room, null, userService.fetchUserByUserName(currentUserName));
		BillingDetails billingDetails = new BillingDetails(
				calculateBill(roomDto.getPrice(), bookingDto.getCheckInDate(), bookingDto.getCheckOutDate()), now);
		bookingDetails.setBillingDetails(billingDetails);
		billingDetails.setBookingDetails(bookingDetails);
		bookingDetailsRepository.saveAndFlush(bookingDetails);

	}

	public Long calculateBill(Long price, LocalDate checkInDate, LocalDate checkOutDate) {
		return ChronoUnit.DAYS.between(checkInDate, checkOutDate) * price;
	}

	public String getLoggedInUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
