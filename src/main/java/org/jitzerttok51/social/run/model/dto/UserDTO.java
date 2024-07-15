package org.jitzerttok51.social.run.model.dto;

import org.jitzerttok51.social.run.model.Sex;

import java.time.LocalDate;

// TODO: Add javadoc
public record UserDTO(
        long id,
        String firstName,
        String lastName,
        String username,
        String email,
        Sex sex,
        LocalDate dateOfBirth
) {
}
