package com.hms.entities;

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
	private Long price;

	@Column(name = "description", length = 400)
	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	// constructors, getters, and setters

	public RoomDefinition() {
	}

	public RoomDefinition(String roomCode, String roomType, int maxGuests, Long price, String description, String imageUrl) {
		this.roomCode = roomCode;
		this.roomType = roomType;
		this.maxGuests = maxGuests;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
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

	public Long getPrice() {
		return price;
	}

	public void setprice(Long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
