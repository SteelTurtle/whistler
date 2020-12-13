package org.gorillacorp.whistler.domain.service;

public class WhistleNotFoundException extends RuntimeException {
    public WhistleNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
