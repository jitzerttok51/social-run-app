package org.jitzerttok51.social.run.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordMatcher implements ConstraintValidator<PasswordMatch, PasswordsMatch> {

    @Override
    public boolean isValid(PasswordsMatch value, ConstraintValidatorContext context) {
        var p1 = value.getPassword();
        var p2 = value.getConfirmPassword();
        return p1 != null && p1.equals(p2);
    }
}
