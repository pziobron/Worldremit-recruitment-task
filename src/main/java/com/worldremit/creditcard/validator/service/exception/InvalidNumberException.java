package com.worldremit.creditcard.validator.service.exception;

public class InvalidNumberException extends IllegalArgumentException {

    public InvalidNumberException(String msg) {
        super(msg);
    }
}

