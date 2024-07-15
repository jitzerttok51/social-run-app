package org.jitzerttok51.social.run.service;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationService {

    <T> Set<ConstraintViolation<?>> validate(T object);
}
