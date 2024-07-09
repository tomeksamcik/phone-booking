package org.example.exceptions;

public class PhoneAlreadyBookedException extends Exception {

    public PhoneAlreadyBookedException(final String message) {
        super(message);
    }
}
