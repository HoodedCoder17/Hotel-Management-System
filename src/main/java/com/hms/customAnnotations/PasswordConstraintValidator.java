
package com.hms.customAnnotations;

import java.util.Arrays;

import org.passay.AllowedCharacterRule;
import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public void initialize(ValidPassword constraintAnnotation) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {

		CharacterCharacteristicsRule characterCharacteristicsRule = new CharacterCharacteristicsRule(1,
				new CharacterRule(EnglishCharacterData.LowerCase, 1),
				new CharacterRule(EnglishCharacterData.UpperCase, 1));

		PasswordValidator validator = new PasswordValidator(Arrays.asList(new LengthRule(8, 30),
				characterCharacteristicsRule,
				new AllowedCharacterRule(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
						'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
						'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
						'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '@', '#', '$', '!', '*' }),
				new WhitespaceRule(), new CharacterRule(EnglishCharacterData.Digit),
				new CharacterRule(EnglishCharacterData.Special),
				new CharacterRule(EnglishCharacterData.Alphabetical, 4)));

		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		} else {
			return false;
		}

	}
}
