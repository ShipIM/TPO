package org.example.third.exception;

public class MissingOwnerException extends RuntimeException {

    public MissingOwnerException(String message) {
        super(message);
    }

}
