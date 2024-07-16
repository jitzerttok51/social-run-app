package org.jitzerttok51.social.run.controller;

import lombok.RequiredArgsConstructor;
import org.jitzerttok51.social.run.model.dto.AuthRequestDTO;
import org.jitzerttok51.social.run.model.dto.AuthResponseDTO;
import org.jitzerttok51.social.run.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping
    public AuthResponseDTO requestKey(@RequestBody AuthRequestDTO request) {
        return service.login(request);
    }
}