package com.hms.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.RoomDefinition;
import com.hms.repositories.RoomDefinitionRepository;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	RoomDefinitionRepository roomDefinitionRepository;
	
	public ArrayList<RoomDefinition> fetchRoomDefinitions() {
		
		return (ArrayList<RoomDefinition>) roomDefinitionRepository.findAll();
	}

}
