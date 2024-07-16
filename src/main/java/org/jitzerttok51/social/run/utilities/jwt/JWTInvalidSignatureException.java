package org.jitzerttok51.social.run.utilities.jwt;

public class JWTInvalidSignatureException extends Exception {

    JWTInvalidSignatureException(String message) {
        super(message);
    }

    JWTInvalidSignatureException(String message, Throwable t) {
        super(message, t);
    }
}
