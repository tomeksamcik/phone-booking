package org.example.services.exceptions;

public class PhoneAlreadyBookedException extends Exception {

    public PhoneAlreadyBookedException(final String message) {
        super(message);
    }
}
