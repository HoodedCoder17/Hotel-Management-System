package com.hms.customAnnotations;

import com.hms.dto.UserDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
implements ConstraintValidator<PasswordMatches, Object> {
  
  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
  }
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){
      UserDto user = (UserDto) obj;
      boolean isValid = user.getPassword().equals(user.getMatchingPassword());
      if(!isValid){
           context.disableDefaultConstraintViolation();
          context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                  .addPropertyNode( "matchingPassword" ).addConstraintViolation();
      }
      return isValid;
  }
}