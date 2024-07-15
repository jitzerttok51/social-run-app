package org.jitzerttok51.social.run.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.jitzerttok51.social.run.service.UserService;

@RequiredArgsConstructor
public class UsernameTakenImpl implements ConstraintValidator<UsernameTaken, String> {

    private final UserService service;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !service.usernameTaken(username);
    }
}
