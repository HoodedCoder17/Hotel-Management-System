package com.hms.customAnnotations;

import java.time.LocalDate;

import com.hms.dto.SearchParameterDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckOutGreaterThanCheckinValidator
		implements ConstraintValidator<CheckOutGreaterThanCheckin, SearchParameterDTO> {

	@Override
	public boolean isValid(SearchParameterDTO search, ConstraintValidatorContext context) {
		String message = "Check-Out date should be after Check-In Date!";
		String property = "checkOut";
		boolean isValid = true;
		LocalDate now = LocalDate.now();
		if(search.getCheckIn() == null) {
			message = "Please select a Check-In Date";
			property = "checkIn";
			isValid = false;
		} else if(search.getCheckOut() == null) {
			message = "Please select a Check-Out Date";
			isValid = false;
			property = "checkOut";
		} else if (!search.getCheckIn().isAfter(now)) {
			message = "Please select a Check-In Date of future";
			isValid = false;
			property = "checkIn";
		}
		else {
			isValid = search.getCheckOut().isAfter(search.getCheckIn());
			property = "checkOut";
		}
		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(property).addConstraintViolation();
		}
		return isValid;
	}

}
