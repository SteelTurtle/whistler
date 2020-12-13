package org.gorillacorp.whistler.domain.service;

public class UnknownUserException extends RuntimeException {
    public UnknownUserException(String errorMessage) {
        super(errorMessage);
    }
}
