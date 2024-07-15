package org.jitzerttok51.social.run.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.passay.PasswordData;
import org.passay.PasswordValidator;

@RequiredArgsConstructor
public class ValidPasswordImpl implements ConstraintValidator<ValidPassword, String> {

    private final PasswordValidator validator;

    @Override
    public boolean isValid(String pass, ConstraintValidatorContext ctx) {
        if(pass == null) {
            return false;
        }
        var result = validator.validate(new PasswordData(pass));
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(
                        String.join(",", validator.getMessages(result)))
                .addConstraintViolation();
        return result.isValid();
    }
}
