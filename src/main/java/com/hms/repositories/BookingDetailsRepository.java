package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.BookingDetails;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {

}
