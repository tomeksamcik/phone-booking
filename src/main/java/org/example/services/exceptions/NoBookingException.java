package org.example.services.exceptions;

public class NoBookingException extends Exception {

    public NoBookingException(final String message) {
        super(message);
    }
}
