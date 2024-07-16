package org.jitzerttok51.social.run.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jitzerttok51.social.run.exceptions.ServerException;
import org.jitzerttok51.social.run.model.dto.AuthRequestDTO;
import org.jitzerttok51.social.run.model.dto.AuthResponseDTO;
import org.jitzerttok51.social.run.model.dto.UserDTO;
import org.jitzerttok51.social.run.service.AuthenticationService;
import org.jitzerttok51.social.run.service.UserService;
import org.jitzerttok51.social.run.utilities.jwt.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private static final long DURATION_IN_MIN = 30;

    private JWTEntry keyEntry;

    @PostConstruct
    public void setup() {
        JWTKeystore keystore = JWTKeystore.fromClasspath("token-key-keystore", "changeit");
        keyEntry = keystore.getEntry("token-key", "changeit");
    }


    private AuthResponseDTO generateJWT(UserDTO user) {
        var claims = JWTUtils.newClaims();
        claims.setSubject("User Details");
        claims.setIssuer("social-app");
        claims.addClaim("username", user.username());
        claims.addClaim("firstName", user.firstName());
        claims.addClaim("lastname", user.lastName());
        var exp = LocalDateTime.now().plusMinutes(DURATION_IN_MIN);
        claims.setExpiration(exp);
        var token = JWTUtils.sign(claims, keyEntry);
        return new AuthResponseDTO(token, exp, "Bearer");
    }




    @Override
    public AuthResponseDTO login(AuthRequestDTO request) {
        ServerException e = new ServerException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        UserDTO user = userService.findUserByUsername(request.username()).orElseThrow(() -> e);
        if (!userService.authenticateUser(request.username(), request.password())) {
            throw e;
        }

        return generateJWT(user);
    }

    @Override
    public void loginWithKey(String token) {
        try {
            var claims = JWTUtils.verify(token, keyEntry);
            authenticate(getUsername(claims), claims.getIssuedAt());
        } catch (JWTInvalidClaimsException | JWTExpiredException | JWTInvalidSignatureException |
                 UsernameNotFoundException e) {
            throw new ServerException("Invalid JWT token", HttpStatus.UNAUTHORIZED);
        }
    }

    private void authenticate(String username, LocalDateTime jwtCreationDate) {
        var user = userService.loadUserByUsername(username, jwtCreationDate);
        var authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                user.getAuthorities());
        if (SecurityContextHolder
                .getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    private String getUsername(JWTClaims claims) {
        return claims.getClaim("username");
    }
}
