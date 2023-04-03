package com.hms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingDto {

	private Long bookingId;

	private Long roomNumber;

	private String roomCode;

	private LocalDate checkInDate;

	private LocalDate checkOutDate;
	
	private Long noOfGuests;

	private Long billingId;

	private BigDecimal totalAmount;

	private LocalDate paymentDate;

	public BookingDto() {
		super();
	}

	public BookingDto(Long roomNumber) {
		super();
		this.roomNumber = roomNumber;
	}

	public BookingDto(Long bookingId, Long roomNumber, String roomCode, LocalDate checkInDate, LocalDate checkOutDate, Long noOfGuests,
			Long billingId, BigDecimal totalAmount, LocalDate paymentDate) {
		super();
		this.bookingId = bookingId;
		this.roomNumber = roomNumber;
		this.roomCode = roomCode;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.noOfGuests = noOfGuests;
		this.billingId = billingId;
		this.totalAmount = totalAmount;
		this.paymentDate = paymentDate;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "BookingDto [bookingId=" + bookingId + ", roomNumber=" + roomNumber + ", roomCode=" + roomCode
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", noOfGuests=" + noOfGuests
				+ ", billingId=" + billingId + ", totalAmount=" + totalAmount + ", paymentDate=" + paymentDate + "]";
	}

}
