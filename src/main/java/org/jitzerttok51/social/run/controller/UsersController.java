package org.jitzerttok51.social.run.controller;

import lombok.RequiredArgsConstructor;
import org.jitzerttok51.social.run.model.dto.UserDTO;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;
import org.jitzerttok51.social.run.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> register(
            @RequestBody UserRegisterDTO registerDTO,
            @RequestParam(value = "dryRun", required = false) boolean dryRun) {
        var result = userService.registerUser(registerDTO, dryRun);
        return ResponseEntity
                .status(dryRun ? HttpStatus.OK : HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> findUser(@PathVariable String username) {
        return ResponseEntity.of(userService.findUserByUsername(username));
    }
}
