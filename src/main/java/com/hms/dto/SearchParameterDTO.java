package com.hms.dto;

import java.time.LocalDate;

import com.hms.customAnnotations.CheckOutGreaterThanCheckin;

@CheckOutGreaterThanCheckin
public class SearchParameterDTO {
	
	private String preferredRoomType;
	
	private LocalDate checkIn;
	
	private LocalDate checkOut;
	
	private Long budget;

	public SearchParameterDTO() {
		super();
	}

	public SearchParameterDTO(String preferredRoomType, LocalDate checkIn, LocalDate checkOut, Long budget) {
		super();
		this.preferredRoomType = preferredRoomType;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.budget = budget;
	}

	public String getPreferredRoomType() {
		return preferredRoomType;
	}

	public void setPreferredRoomType(String preferredRoomType) {
		this.preferredRoomType = preferredRoomType;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "SearchParameterDTO [preferredRoomType=" + preferredRoomType + ", checkIn=" + checkIn + ", checkOut="
				+ checkOut + ", budget=" + budget + "]";
	}
	
}
