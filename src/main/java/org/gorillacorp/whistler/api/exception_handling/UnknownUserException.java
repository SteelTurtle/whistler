package org.gorillacorp.whistler.api.exception_handling;

public class UnknownUserException extends RuntimeException {
    public UnknownUserException(String errorMessage) {
        super(errorMessage);
    }
}
