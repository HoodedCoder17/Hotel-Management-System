package com.hms.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "maintainence")
public class Maintainence {
	
	@Id
	@Column(name = "maintainence_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long maintainenceId;
	
	@Column(name = "maintainence_start_date")
    private LocalDate maintainenceStartDate;
    
    @Column(name = "maintainence_end_date")
    private LocalDate maintainenceEndDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    
	public Maintainence() {
		super();
	}

	public Maintainence(LocalDate maintainenceStartDate, LocalDate maintainenceEndDate,
			Room room) {
		super();
		this.maintainenceStartDate = maintainenceStartDate;
		this.maintainenceEndDate = maintainenceEndDate;
		this.room = room;
	}

	public Long getMaintainenceId() {
		return maintainenceId;
	}

	public void setMaintainenceId(Long maintainenceId) {
		this.maintainenceId = maintainenceId;
	}

	public LocalDate getMaintainenceStartDate() {
		return maintainenceStartDate;
	}

	public void setMaintainenceStartDate(LocalDate maintainenceStartDate) {
		this.maintainenceStartDate = maintainenceStartDate;
	}

	public LocalDate getMaintainenceEndDate() {
		return maintainenceEndDate;
	}

	public void setMaintainenceEndDate(LocalDate maintainenceEndDate) {
		this.maintainenceEndDate = maintainenceEndDate;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
