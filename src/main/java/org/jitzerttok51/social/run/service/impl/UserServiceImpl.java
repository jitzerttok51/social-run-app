package org.jitzerttok51.social.run.service.impl;

import org.jitzerttok51.social.run.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;
import org.jitzerttok51.social.run.repository.UserRepository;
import org.jitzerttok51.social.run.service.UserService;
import org.jitzerttok51.social.run.service.ValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ValidationService validator;

    @Override
    public boolean emailTaken(String email) {
        return repository.emailExists(email);
    }

    @Override
    public boolean usernameTaken(String username) {
        return repository.usernameExists(username);
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterForm) {
        var result = validator.validate(userRegisterForm);
        if(!result.isEmpty()) {
            throw new ValidationException("Invalid registration values", result);
        }
    }
}
