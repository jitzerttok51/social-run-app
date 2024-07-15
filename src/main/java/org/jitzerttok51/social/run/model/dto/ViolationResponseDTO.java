package org.jitzerttok51.social.run.model.dto;

import java.util.Collection;

// TODO: Add javadoc
public record ViolationResponseDTO(
        String message,
        Collection<ViolationDTO> errors
) { }
