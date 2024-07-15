package org.jitzerttok51.social.run.controller;

import lombok.RequiredArgsConstructor;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;
import org.jitzerttok51.social.run.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @PostMapping
    public String register(@RequestBody UserRegisterDTO registerDTO) {
        userService.registerUser(registerDTO);
        return "Success";
    }
}
