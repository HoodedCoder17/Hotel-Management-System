package com.hms.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dto.SearchResultDto;
import com.hms.entities.Room;
import com.hms.entities.RoomDefinition;
import com.hms.repositories.RoomDefinitionRepository;
import com.hms.repositories.RoomRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	RoomDefinitionRepository roomDefinitionRepository;

	@Autowired
	RoomRepository roomRepository;

	public ArrayList<RoomDefinition> fetchRoomDefinitions() {

		return (ArrayList<RoomDefinition>) roomDefinitionRepository.findAll();
	}

	@Override
	public RoomDefinition fetchRoomDefinitionByCode(String roomCode) {

		return roomDefinitionRepository.findByRoomCode(roomCode);
	}

	@Override
	public Room fetchRoomByCode(String roomCode) {

		return roomRepository.findByRoomCode(roomCode);
	}

	@Override
	public Room fetchRoomById(String roomId) {

		return roomRepository.findByRoomId(roomId);
	}

	@Override
	public SearchResultDto setSearchResultDto(String roomCode) {
		Room room = fetchRoomByCode(roomCode);
		RoomDefinition roomDefinition = fetchRoomDefinitionByCode(roomCode);
		return new SearchResultDto(room.getRoomNumber(), room.getRoomCode(), roomDefinition.getRoomType(),
				roomDefinition.getMaxGuests(), roomDefinition.getPrice(), roomDefinition.getDescription(),
				roomDefinition.getImageUrl());
	}

	@Override
	public ArrayList<SearchResultDto> setSearchResultDtoAll() {
		ArrayList<SearchResultDto> searchResultDtoList = new ArrayList<SearchResultDto>();

		for (Room room : roomRepository.findAll()) {
			RoomDefinition roomDefinition = fetchRoomDefinitionByCode(room.getRoomCode());
			searchResultDtoList.add(new SearchResultDto(room.getRoomNumber(), room.getRoomCode(),
					roomDefinition.getRoomType(), roomDefinition.getMaxGuests(), roomDefinition.getPrice(),
					roomDefinition.getDescription(), roomDefinition.getImageUrl()));
		}
		return searchResultDtoList;
	}

}
