package org.jitzerttok51.social.run.service;

import org.jitzerttok51.social.run.exceptions.ValidationException;
import org.jitzerttok51.social.run.model.dto.UserDTO;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;

// TODO: Add javadoc
public interface UserService {

    boolean emailTaken(String email);

    boolean usernameTaken(String username);

    UserDTO registerUser(UserRegisterDTO userRegisterForm, boolean dryRun) throws ValidationException;
}
