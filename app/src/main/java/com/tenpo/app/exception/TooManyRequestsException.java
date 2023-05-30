package com.tenpo.app.exception;

import org.springframework.http.HttpStatus;

public class TooManyRequestsException extends RuntimeException {
    private final HttpStatus httpStatus;

    public TooManyRequestsException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
