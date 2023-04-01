package com.hms.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "room_code")
    private String roomCode;

    @OneToMany(mappedBy = "room")
    private List<BookingDetails> bookingDetailsList;

    // constructors, getters, and setters

    public Room() {}

    public Room(Long roomId, Long roomNumber, String roomCode, List<BookingDetails> bookingDetailsList) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.roomCode = roomCode;
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

	public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public List<BookingDetails> getBookingDetailsList() {
        return bookingDetailsList;
    }

    public void setBookingDetailsList(List<BookingDetails> bookingDetailsList) {
        this.bookingDetailsList = bookingDetailsList;
    }
}
