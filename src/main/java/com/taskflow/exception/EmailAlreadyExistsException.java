package com.taskflow.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(
                "Email already exists: " + message
        );
    }
}
