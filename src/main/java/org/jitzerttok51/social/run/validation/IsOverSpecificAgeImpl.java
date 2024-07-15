package org.jitzerttok51.social.run.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class IsOverSpecificAgeImpl implements ConstraintValidator<IsOverSpecificAge, LocalDate> {

    private int minAge;

    @Override
    public void initialize(IsOverSpecificAge constraintAnnotation) {
        this.minAge = constraintAnnotation.minAge();
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        var diff = Period.between(dateOfBirth, LocalDate.now());
        return diff.getYears() >= minAge;
    }
}
