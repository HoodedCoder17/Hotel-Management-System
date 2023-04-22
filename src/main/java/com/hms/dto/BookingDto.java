package com.hms.dto;

import java.time.LocalDate;

public class BookingDto {

	private Long bookingId;

	private Long roomNumber;

	private String roomCode;
	
	private String roomType;

	private LocalDate checkInDate;

	private LocalDate checkOutDate;
	
	private Long noOfGuests;

	private Long billingId;

	private Long totalAmount;

	private LocalDate paymentDate;
	
	private String userName;
	
	private String userFullName;
	
	private String bookingStatus;

	public BookingDto() {
		super();
	}

	public BookingDto(Long roomNumber) {
		super();
		this.roomNumber = roomNumber;
	}

	public BookingDto(Long bookingId, Long roomNumber, String roomCode, String roomType, LocalDate checkInDate, LocalDate checkOutDate, Long noOfGuests,
			Long billingId, Long totalAmount, LocalDate paymentDate, String userName, String userFullName, String bookingStatus) {
		super();
		this.bookingId = bookingId;
		this.roomNumber = roomNumber;
		this.roomCode = roomCode;
		this.roomType = roomType;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.noOfGuests = noOfGuests;
		this.billingId = billingId;
		this.totalAmount = totalAmount;
		this.paymentDate = paymentDate;
		this.userName = userName;
		this.userFullName = userFullName;
		this.bookingStatus = bookingStatus;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Long roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Long getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(Long noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	public Long getBillingId() {
		return billingId;
	}

	public void setBillingId(Long billingId) {
		this.billingId = billingId;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	@Override
	public String toString() {
		return "BookingDto [bookingId=" + bookingId + ", roomNumber=" + roomNumber + ", roomCode=" + roomCode
				+ ", roomType=" + roomType + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", noOfGuests=" + noOfGuests + ", billingId=" + billingId + ", totalAmount=" + totalAmount
				+ ", paymentDate=" + paymentDate + ", userName=" + userName + ", userFullName=" + userFullName
				+ ", bookingStatus=" + bookingStatus + "]";
	}

}
