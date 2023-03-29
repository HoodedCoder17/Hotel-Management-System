package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.RoomDefinition;

public interface RoomDefinitionRepository extends JpaRepository<RoomDefinition, String> {

}
