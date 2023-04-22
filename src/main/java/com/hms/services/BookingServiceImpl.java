package com.hms.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
		String currentUserName = userService.getLoggedInUserName();
		RoomDto roomDto = setRoomDto(bookingDto.getRoomNumber());
		Room room = roomRepository.findByRoomNumber(roomDto.getRoomNumber());
		BookingDetails bookingDetails = new BookingDetails(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate(),
				bookingDto.getNoOfGuests(), room, null, userService.fetchUserByUserName(currentUserName), true);
		BillingDetails billingDetails = new BillingDetails(
				calculateBill(roomDto.getPrice(), bookingDto.getCheckInDate(), bookingDto.getCheckOutDate()), now);
		bookingDetails.setBillingDetails(billingDetails);
		billingDetails.setBookingDetails(bookingDetails);
		bookingDetailsRepository.saveAndFlush(bookingDetails);

	}

	public Long calculateBill(Long price, LocalDate checkInDate, LocalDate checkOutDate) {
		return ChronoUnit.DAYS.between(checkInDate, checkOutDate) * price;
	}

	@Override
	public Boolean checkRoomAvailability(Long roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
		if (roomRepository.checkRoomAvailabiltyBasedOnRoomNumber(roomNumber, checkInDate, checkOutDate) >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkIfGuestsUnderTheRoomLimits(Long expectedGuests, Long roomNumber) {
		if (expectedGuests <= roomRepository.getMaxGuestsForTheRoom(roomNumber)) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkIfCheckInDateIsInFuture(LocalDate checkInDate) {
		LocalDate now = LocalDate.now();
		return checkInDate.isAfter(now);
	}

	@Override
	public Boolean checkIfCheckOutDateIsGreaterThanCheckInDate(LocalDate checkInDate, LocalDate checkOutDate) {
		return checkOutDate.isAfter(checkInDate);
	}

	@Override
	public ArrayList<BookingDto> findAllBookingsOfLoggedInUser() {
		ArrayList<BookingDto> BookingDtoList = new ArrayList<BookingDto>();
		ArrayList<BookingDetails> bookingDetailsOfLoggedInUser = bookingDetailsRepository
				.findByUser(userService.fetchUserByUserName(userService.getLoggedInUserName()));
		for (BookingDetails bookingDetail : bookingDetailsOfLoggedInUser) {
			if (bookingDetail.getStatus() != false) {
				BookingDtoList.add(new BookingDto(bookingDetail.getBookingId(), bookingDetail.getRoom().getRoomNumber(),
						bookingDetail.getRoom().getRoomDefinition().getRoomCode(),
						bookingDetail.getRoom().getRoomDefinition().getRoomType(), bookingDetail.getCheckInDate(),
						bookingDetail.getCheckOutDate(), bookingDetail.getNoOfGuests(),
						bookingDetail.getBillingDetails().getBillingId(),
						bookingDetail.getBillingDetails().getTotalAmount(),
						bookingDetail.getBillingDetails().getPaymentDate()));
			}
		}
		return BookingDtoList;
	}

	@Override
	public Boolean cancelBooking(Long bookingId) {
		BookingDetails bookingDetails = bookingDetailsRepository.findByBookingId(bookingId);
		if (checkIfBookingIsInFuture(bookingDetails.getCheckInDate())) {
			bookingDetailsRepository.cancelBooking(bookingId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean checkIfBookingIsInFuture(LocalDate checkInDate) {
		LocalDate now = LocalDate.now();
		return checkInDate.isAfter(now);
	}

	@Override
	public Long fetchAmountOfBookingsByUserId(Long userId) {
		return bookingDetailsRepository.fetchAmountOfBookingsByUserId(userId);
	}
	
	@Override
	public Long fetchBookingValueByUserId(Long userId) {
		return bookingDetailsRepository.fetchBookingValueByUserId(userId);
	}

}
