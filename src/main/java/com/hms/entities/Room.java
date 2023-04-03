package com.hms.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;
    
    @Column(name = "room_number")
    private Long roomNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_code", referencedColumnName = "room_code", updatable = false)
    private RoomDefinition roomDefinition;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<BookingDetails> bookingDetailsList;

    // constructors, getters, and setters

    public Room() {}

    public Room(Long roomId, Long roomNumber, RoomDefinition roomDefinition, List<BookingDetails> bookingDetailsList) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.roomDefinition = roomDefinition;
		this.bookingDetailsList = bookingDetailsList;
	}

	public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    
    public Long getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Long roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomDefinition getRoomDefinition() {
        return roomDefinition;
    }

    public void setRoomDefinition(RoomDefinition roomDefinition) {
        this.roomDefinition = roomDefinition;
    }

    public List<BookingDetails> getBookingDetailsList() {
        return bookingDetailsList;
    }

    public void setBookingDetailsList(List<BookingDetails> bookingDetailsList) {
        this.bookingDetailsList = bookingDetailsList;
    }

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber + ", roomDefinition=" + roomDefinition
				+ ", bookingDetailsList=" + bookingDetailsList + "]";
	}
    
}
