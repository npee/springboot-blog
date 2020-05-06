package com.npee.npeeblog.advice.exception;

public class CustomUserExistsException extends RuntimeException{
    public CustomUserExistsException() {
        super();
    }

    public CustomUserExistsException(String message) {
        super(message);
    }

    public CustomUserExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
