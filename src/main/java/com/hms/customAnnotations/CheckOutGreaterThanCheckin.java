package com.hms.customAnnotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckOutGreaterThanCheckinValidator.class)
@Documented
public @interface CheckOutGreaterThanCheckin {
	String message() default "Check-Out date should be after Check-In Date!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
