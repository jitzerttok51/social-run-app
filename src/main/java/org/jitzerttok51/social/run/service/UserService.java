package org.jitzerttok51.social.run.service;

import org.jitzerttok51.social.run.exceptions.ValidationException;
import org.jitzerttok51.social.run.model.dto.UserDTO;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;
import org.jitzerttok51.social.run.model.dto.UserSecretDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Optional;

// TODO: Add javadoc
public interface UserService {

    boolean emailTaken(String email);

    boolean usernameTaken(String username);

    Optional<UserDTO> findUserByUsername(String username);

    UserDetails loadUserByUsername(String username, LocalDateTime verifyDate);

    boolean authenticateUser(String username, String password);

    UserDTO registerUser(UserRegisterDTO userRegisterForm, boolean dryRun) throws ValidationException;
}
