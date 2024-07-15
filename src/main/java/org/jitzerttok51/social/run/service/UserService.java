package org.jitzerttok51.social.run.service;

public interface UserService {

    boolean emailTaken(String email);

    boolean usernameTaken(String username);
}
