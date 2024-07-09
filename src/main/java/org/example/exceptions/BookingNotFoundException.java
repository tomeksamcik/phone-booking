package org.example.exceptions;

public class BookingNotFoundException extends Exception {

    public BookingNotFoundException(final String message) {
        super(message);
    }
}
