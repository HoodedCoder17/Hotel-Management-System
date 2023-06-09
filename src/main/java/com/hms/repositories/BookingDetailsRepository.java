package com.hms.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.hms.entities.BookingDetails;
import com.hms.entities.User;

@Transactional
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {

	ArrayList<BookingDetails> findByUser(User user);
	
	ArrayList<BookingDetails> findAllByOrderByCheckInDate();

	BookingDetails findByBookingId(Long bookingId);

	@Modifying
	@Query("UPDATE BookingDetails BD set status = false where BD.bookingId = ?1 ")
	void cancelBooking(Long bookingId);

	@Query("SELECT COUNT(BD.bookingId) FROM BookingDetails BD JOIN BillingDetails BID ON BD.billingDetails = BID.billingId "
			+ "JOIN User U ON U.userId = BD.user where U.userId = ?1 ")
	Long fetchAmountOfBookingsByUserId(Long UserId);
	
	@Query("SELECT SUM(BID.totalAmount) FROM BookingDetails BD JOIN BillingDetails BID ON BD.billingDetails = BID.billingId "
			+ "JOIN User U ON U.userId = BD.user where U.userId = ?1 ")
	Long fetchBookingValueByUserId(Long UserId);
	
}
