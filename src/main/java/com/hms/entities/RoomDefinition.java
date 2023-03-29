package com.hms.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_definition")
public class RoomDefinition {

	@Id
	@Column(name = "room_code")
	private String roomCode;

	@Column(name = "room_type")
	private String roomType;

	@Column(name = "max_guests")
	private int maxGuests;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "description")
	private String description;

	// constructors, getters, and setters

	public RoomDefinition() {
	}

	public RoomDefinition(String roomCode, String roomType, int maxGuests, BigDecimal price, String description) {
		this.roomCode = roomCode;
		this.roomType = roomType;
		this.maxGuests = maxGuests;
		this.price = price;
		this.description = description;
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

	public int getMaxGuests() {
		return maxGuests;
	}

	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}

	public BigDecimal getprice() {
		return price;
	}

	public void setprice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
