package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.BillingDetails;

public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Long> {

}
