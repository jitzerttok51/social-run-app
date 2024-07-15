package org.jitzerttok51.social.run.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

// TODO: Add javadoc
@Target({ FIELD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailTakenImpl.class)
@Documented
public @interface EmailTaken {
    String message() default "Email taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}