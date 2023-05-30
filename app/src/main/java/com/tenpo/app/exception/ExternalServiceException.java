package com.tenpo.app.exception;

public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String errorMessage) {
        super(errorMessage);
    }
}
