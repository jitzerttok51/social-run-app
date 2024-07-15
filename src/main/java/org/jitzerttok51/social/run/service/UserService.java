package org.jitzerttok51.social.run.service;

import org.jitzerttok51.social.run.exceptions.ValidationException;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;

public interface UserService {

    boolean emailTaken(String email);

    boolean usernameTaken(String username);

    void registerUser(UserRegisterDTO userRegisterForm) throws ValidationException;
}
