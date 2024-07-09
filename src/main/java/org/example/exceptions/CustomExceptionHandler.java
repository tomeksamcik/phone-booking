package org.example.exceptions;

import org.example.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .error(String.format("%s : %s", ex.getFieldError().getField(), ex.getFieldError().getDefaultMessage()))
                        .build());
    }

    @ExceptionHandler({PhoneAlreadyBookedException.class, BookingNotFoundException.class, PhoneNotFoundException.class})
    public ResponseEntity<Error> handleMultipleExceptions(final Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .error(ex.getMessage())
                        .build());
    }
}
