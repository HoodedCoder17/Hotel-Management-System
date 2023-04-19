package com.hms.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking_details")
public class BookingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booking_id")
	private Long bookingId;

	@Column(name = "check_in_date")
	private LocalDate checkInDate;

	@Column(name = "check_out_date")
	private LocalDate checkOutDate;

	@Column(name = "no_of_guests")
	private Long noOfGuests;

	@ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_id")
	private BillingDetails billingDetails;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	// Status will be set to True when booking is active, status is false when booking is cancelled
	@Column(name = "status")
	private Boolean status;

	// constructors, getters, and setters

	public BookingDetails() {
	}

	public BookingDetails(LocalDate checkInDate, LocalDate checkOutDate, Long noOfGuests, Room room,
			BillingDetails billingDetails, User user, Boolean status) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.noOfGuests = noOfGuests;
		this.room = room;
		this.billingDetails = billingDetails;
		this.user = user;
		this.status = status;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public BillingDetails getBillingDetails() {
		return billingDetails;
	}

	public void setBillingDetails(BillingDetails billingDetails) {
		this.billingDetails = billingDetails;
	}

	public Long getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(Long noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
