package com.hms.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.hms.dto.UserDto;
import com.hms.entities.User;
import com.hms.exceptions.UserAlreadyExistException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface UserService {

	User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;
	
	User fetchUserByUserName(String userName);
	
	ArrayList<User> fetchAllUsers();
	
	String getLoggedInUserName();
	
	UserDto convertUserToUserDto(User user);
	
	ArrayList<UserDto> convertUserListToUserDtoList(ArrayList<User> userList);
	
	Boolean inactivateUser(Long userId);
	
	Boolean activateUser(Long userId);

}
