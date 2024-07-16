package org.jitzerttok51.social.run.service.impl;

import org.jitzerttok51.social.run.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jitzerttok51.social.run.model.dto.UserDTO;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;
import org.jitzerttok51.social.run.model.mapping.UserEntityMapper;
import org.jitzerttok51.social.run.repository.UserRepository;
import org.jitzerttok51.social.run.service.UserService;
import org.jitzerttok51.social.run.service.ValidationService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final ValidationService validator;
    private final UserEntityMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public boolean emailTaken(String email) {
        return repository.emailExists(email);
    }

    @Override
    public boolean usernameTaken(String username) {
        return repository.usernameExists(username);
    }

    @Override
    @Transactional
    public UserDTO registerUser(UserRegisterDTO userRegisterForm, boolean dryRun) {
        var result = validator.validate(userRegisterForm);
        if(!result.isEmpty()) {
            throw new ValidationException("Invalid registration values", result);
        }
        var e = mapper.map(userRegisterForm);
        if(dryRun) {
            e.setId(-1);
        } else {
            e.setHash(encoder.encode(userRegisterForm.getPassword()));
            repository.save(e);
        }
        return mapper.map(e);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Cannot find user with username "+username));
        return new User(user.getUsername(), user.getHash(), List.of());
    }
}
