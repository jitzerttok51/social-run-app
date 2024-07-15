package org.jitzerttok51.social.run.controller;

import org.jitzerttok51.social.run.exceptions.ValidationException;
import org.jitzerttok51.social.run.model.dto.ViolationDTO;
import org.jitzerttok51.social.run.model.dto.ViolationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ViolationResponseDTO> handleValidationResponse(ValidationException e) {
        var violations = e.getViolations().stream().map(ViolationDTO::new).toList();
        var result = new ViolationResponseDTO(e.getMessage(), violations);
        return ResponseEntity
                .status(e.getStatus())
                .body(result);
    }
}
