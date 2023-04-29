package com.hms.dto;

public class PriceChangeDto {
	
	private String roomCode;
	
	private Long newPrice;
	
	public PriceChangeDto() {
		super();
	}

	public PriceChangeDto(String roomCode, Long newPrice) {
		super();
		this.roomCode = roomCode;
		this.newPrice = newPrice;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public Long getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Long newPrice) {
		this.newPrice = newPrice;
	}
	
}
