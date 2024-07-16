package org.jitzerttok51.social.run.model.dto;


import java.time.LocalDateTime;

public record AuthResponseDTO(
    String accessToken,
    LocalDateTime expirationTime,
    String type
){}