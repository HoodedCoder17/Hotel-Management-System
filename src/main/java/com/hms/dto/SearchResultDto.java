package com.hms.dto;

public class SearchResultDto {

	private Long roomNumber;
	
	private String roomCode;
	
	private String roomType;
	
	private int maxGuests;
	
	private Long price;

	private String description;

	private String imageUrl;

	public SearchResultDto() {
		super();
	}

	public SearchResultDto(Long roomNumber, String roomCode, String roomType, int maxGuests, Long price,
			String description, String imageUrl) {
		super();
		this.roomNumber = roomNumber;
		this.roomCode = roomCode;
		this.roomType = roomType;
		this.maxGuests = maxGuests;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
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

	public int getMaxGuests() {
		return maxGuests;
	}

	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
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

	@Override
	public String toString() {
		return "SearchResultDto [roomNumber=" + roomNumber + ", roomCode=" + roomCode + ", roomType=" + roomType
				+ ", maxGuests=" + maxGuests + ", price=" + price + ", description=" + description + ", imageUrl="
				+ imageUrl + "]";
	}
	
}
