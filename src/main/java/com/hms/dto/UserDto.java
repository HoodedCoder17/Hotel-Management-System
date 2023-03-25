package com.hms.dto;

import java.time.LocalDate;

import com.hms.customAnnotations.PasswordMatches;
import com.hms.customAnnotations.ValidEmail;
import com.hms.entities.Role;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {
	
	@NotNull
    @NotEmpty
    private String userName;
	
	@NotNull
    @NotEmpty
    private String firstName;
    
    @NotNull
    @NotEmpty
    private String lastName;
    
    @NotNull
    @NotEmpty
    private String password;
    
    private String matchingPassword;
    
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
    
    @NotNull
    private String role;
    
    @NotNull
    private LocalDate dateOfBirth;

	public UserDto() {
		super();
	}

	public UserDto(@NotNull @NotEmpty String userName, @NotNull @NotEmpty String firstName,
			@NotNull @NotEmpty String lastName, @NotNull @NotEmpty String password, String matchingPassword,
			@NotNull @NotEmpty String email, @NotNull String role, @NotNull LocalDate dateOfBirth) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.email = email;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", matchingPassword=" + matchingPassword + ", email=" + email + ", role=" + role
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}

}
