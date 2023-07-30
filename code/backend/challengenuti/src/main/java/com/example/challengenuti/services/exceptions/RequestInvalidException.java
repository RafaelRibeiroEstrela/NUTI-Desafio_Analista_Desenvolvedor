package com.example.challengenuti.services.exceptions;

public class RequestInvalidException extends RuntimeException {

    public RequestInvalidException(String message) {
        super(message);
    }
}
