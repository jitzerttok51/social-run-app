package org.jitzerttok51.social.run.utilities.jwt;

public class JWTExpiredException extends Exception {

    JWTExpiredException(String message) {
        super(message);
    }

    JWTExpiredException(String message, Throwable t) {
        super(message, t);
    }
}
