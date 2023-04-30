package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.Maintainence;

public interface MaintainenceRepository extends JpaRepository<Maintainence, Long> {

}
