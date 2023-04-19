package com.hms.services;

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
	
	String getLoggedInUserName();

}
