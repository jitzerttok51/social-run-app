package org.jitzerttok51.social.run.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.jitzerttok51.social.run.service.UserService;

@RequiredArgsConstructor
public class EmailTakenImpl implements ConstraintValidator<EmailTaken, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.emailTaken(email);
    }
}
