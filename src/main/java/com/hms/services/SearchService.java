package com.hms.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.hms.entities.RoomDefinition;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface SearchService {
	
	ArrayList<RoomDefinition> fetchRoomDefinitions();

}
