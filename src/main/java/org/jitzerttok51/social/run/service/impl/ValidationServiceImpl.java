package org.jitzerttok51.social.run.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jitzerttok51.social.run.service.ValidationService;
import org.jitzerttok51.social.run.validation.groups.ViolationHelper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final Validator validator;

    @Override
    public <T> Set<ConstraintViolation<?>> validate(T object) {
        var helper = new ViolationHelper();
        helper.validateObject(validator, object);
        return helper.toSet();
    }
}
