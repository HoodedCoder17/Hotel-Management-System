package com.hms.dto;

import java.time.LocalDate;

import com.hms.customAnnotations.PasswordMatches;
import com.hms.customAnnotations.ValidEmail;
import com.hms.customAnnotations.ValidPassword;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@PasswordMatches(message = "Passwords do not match!")
public class UserDto {

	@NotEmpty(message = "User Name field cannot be empty!")
	private String userName;

	@NotEmpty(message = "First Name field cannot be empty!")
	private String firstName;

	@NotEmpty(message = "Last Name field cannot be empty!")
	private String lastName;

	@NotEmpty(message = "Password cannot be empty!")
	@ValidPassword(message="Password can only contain alphabets(atleast 4, with atleast 1 of lower and upper case), atleast 1 each numeral and special characters like ,@#$!*")
	private String password;

	private String matchingPassword;

	@ValidEmail(message = "Please enter a valid mail id!")
	@NotEmpty(message = "Email Id cannot be empty!")
	private String email;

	@NotNull(message = "A Role have to be selected!")
	private String role;

	@NotNull(message = "Date of Birth has to be provided!")
	private LocalDate dateOfBirth;

	public UserDto() {
		super();
	}

	public UserDto(@NotEmpty String userName, @NotEmpty String firstName, @NotEmpty String lastName,
			@NotEmpty String password, String matchingPassword, @NotEmpty String email, @NotNull String role,
			@NotNull LocalDate dateOfBirth) {
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
