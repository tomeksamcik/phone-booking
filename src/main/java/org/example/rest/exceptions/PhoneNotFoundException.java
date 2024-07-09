package org.example.rest.exceptions;

public class PhoneNotFoundException extends Exception {

    public PhoneNotFoundException(final String message) {
        super(message);
    }
}
