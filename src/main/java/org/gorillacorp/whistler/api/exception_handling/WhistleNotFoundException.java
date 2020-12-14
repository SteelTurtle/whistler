package org.gorillacorp.whistler.api.exception_handling;

public class WhistleNotFoundException extends RuntimeException {
    public WhistleNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
