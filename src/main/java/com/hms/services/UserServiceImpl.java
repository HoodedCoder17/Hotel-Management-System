package com.hms.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.dto.UserDto;
import com.hms.entities.Role;
import com.hms.entities.User;
import com.hms.exceptions.UserAlreadyExistException;
import com.hms.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
		if (emailExists(userDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
		}

		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		user.setUserName(userDto.getUserName());
		user.setRole(userDto.getRole().equalsIgnoreCase("USER") ? Role.USER : Role.ADMIN);
		user.setIsActive(Boolean.TRUE);
		user.setEmailId(userDto.getEmail());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setCreatedDate(LocalDateTime.now());
		user.setModifiedDate(LocalDateTime.now());

		return userRepository.save(user);
	}

	private boolean emailExists(String email) {
		return userRepository.findByEmailId(email) != null;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + userName);
		}
		boolean enabled = user.getIsActive();
		boolean accountNonExpired = user.getIsActive();
		boolean credentialsNonExpired = user.getIsActive();
		boolean accountNonLocked = user.getIsActive();

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRole().toString()));
	}

	private static List<GrantedAuthority> getAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public User fetchUserByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		return user;
	}

	public String getLoggedInUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	@Override
	public ArrayList<User> fetchAllUsers() {
		ArrayList<User> userList = (ArrayList<User>) userRepository.findAll();
		return userList;
	}

	@Override
	public UserDto convertUserToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmailId());
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setRole(user.getRole().toString());
		userDto.setIsActive(user.getIsActive() ? "Active" : "InActive");
		return userDto;
	}

	@Override
	public ArrayList<UserDto> convertUserListToUserDtoList(ArrayList<User> userList) {
		ArrayList<UserDto> userDtoList = new ArrayList<UserDto>();
		for (User user : userList) {
			userDtoList.add(convertUserToUserDto(user));
		}
		return userDtoList;
	}

	@Override
	public Boolean inactivateUser(Long userId) {
		Long loggedInUserId = fetchUserByUserName(getLoggedInUserName()).getUserId();
		if (userId.compareTo(loggedInUserId) == 0 ? true : false) {
			userRepository.inactivateUser(userId);
			return false;
		} else {
			return userRepository.inactivateUser(userId) == 1 ? true : false;
		}
	}

	@Override
	public Boolean activateUser(Long userId) {
		return userRepository.activateUser(userId) == 1 ? true : false;
	}

}
