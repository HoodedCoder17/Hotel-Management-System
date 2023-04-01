package com.hms.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.hms.dto.SearchResultDto;
import com.hms.entities.Room;
import com.hms.entities.RoomDefinition;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface SearchService {
	
	ArrayList<RoomDefinition> fetchRoomDefinitions();
	
	Room fetchRoomByCode(String roomCode);
	
	Room fetchRoomById(String roomId);

	RoomDefinition fetchRoomDefinitionByCode(String roomCode);
	
	SearchResultDto setSearchResultDto(String roomCode);
	
	ArrayList<SearchResultDto> setSearchResultDtoAll();

}
