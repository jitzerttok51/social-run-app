package org.jitzerttok51.social.run.utilities.jwt;

public class JWTInvalidClaimsException extends Exception {

    JWTInvalidClaimsException(String message) {
        super(message);
    }

    JWTInvalidClaimsException(String message, Throwable t) {
        super(message, t);
    }
}
