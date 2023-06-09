package com.hms.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "billing_details")
public class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "billing_id")
    private Long billingId;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @OneToOne(mappedBy = "billingDetails")
    private BookingDetails bookingDetails;

    public BillingDetails() {}

    public BillingDetails(Long totalAmount, LocalDate paymentDate) {
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
    }

    public Long getBillingId() {
        return billingId;
    }

    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}

