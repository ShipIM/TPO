package org.example.third.exception;

public class MissingActionException extends RuntimeException {

    public MissingActionException(String message) {
        super(message);
    }

}
