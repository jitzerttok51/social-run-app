package org.jitzerttok51.social.run.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jitzerttok51.social.run.repository.UserRepository;
import org.jitzerttok51.social.run.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public boolean emailTaken(String email) {
        return repository.emailExists(email);
    }

    @Override
    public boolean usernameTaken(String username) {
        return repository.usernameExists(username);
    }
}
