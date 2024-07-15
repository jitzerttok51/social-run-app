package org.jitzerttok51.social.run.exceptions;

import org.springframework.http.HttpStatus;

public class ServerException extends RuntimeException {

    private final HttpStatus status;

    public ServerException(HttpStatus status) {
        this.status = status;
    }

    public ServerException(String message, Throwable t, HttpStatus status) {
        super(message, t);
        this.status = status;
    }

    public ServerException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ServerException(Throwable t, HttpStatus status) {
        super(t);
        this.status = status;
    }
}
