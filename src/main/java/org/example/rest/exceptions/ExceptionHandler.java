package org.example.rest.exceptions;

import org.example.model.Error;
import org.example.services.exceptions.NoBookingException;
import org.example.services.exceptions.PhoneAlreadyBookedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .error(String.format("%s : %s", ex.getFieldError().getField(), ex.getFieldError().getDefaultMessage()))
                        .build());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({PhoneAlreadyBookedException.class, NoBookingException.class, PhoneNotFoundException.class})
    public ResponseEntity<Error> handleMultipleExceptions(final Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .error(ex.getMessage())
                        .build());
    }
}
