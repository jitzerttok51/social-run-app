package org.jitzerttok51.social.run.service;

import org.jitzerttok51.social.run.model.dto.AuthRequestDTO;
import org.jitzerttok51.social.run.model.dto.AuthResponseDTO;

public interface AuthenticationService {

    AuthResponseDTO login(AuthRequestDTO request);

    void loginWithKey(String token);
}
