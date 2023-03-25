package com.hms.entities;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="booking_details")
public class BookingDetails {

	@Id
	@Column(name="booking_id")
	private Long bookingId;
	
	@OneToMany(mappedBy="bookingDetails")
	private ArrayList<Room> rooms;
	
	@Column(name="amount")
	private Long amount;

	public BookingDetails() {
		super();
	}

	public BookingDetails(Long bookingId, ArrayList<Room> rooms, Long amount) {
		super();
		this.bookingId = bookingId;
		this.rooms = rooms;
		this.amount = amount;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", rooms=" + rooms + ", amount=" + amount + "]";
	}
	
}
