package org.example.rest;

import jakarta.validation.Valid;
import org.example.model.Booking;
import org.example.rest.exceptions.PhoneNotFoundException;
import org.example.services.BookingService;
import org.example.services.PhoneService;
import org.example.services.exceptions.NoBookingException;
import org.example.services.exceptions.PhoneAlreadyBookedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/*
TODO:
- Write unit tests for controllers
- Write readme
 */
@RestController
@RequestMapping("bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public Set<Booking> getBookings() {
        return bookingService.findAll();
    }

    @PostMapping
    public Booking createBooking(@Valid @RequestBody final Booking booking) throws PhoneAlreadyBookedException, PhoneNotFoundException {
        /*
        Phone name is irrelevant as the phone is read from the data store anyway
         */
        var phone = phoneService.findById(booking.getPhone().getId())
                .orElseThrow(() -> new PhoneNotFoundException(String.format("No phone found for the given id: %d",
                        booking.getPhone().getId())));
        return bookingService.create(booking.toBuilder().phone(phone).build());
    }

    @DeleteMapping
    public ResponseEntity<String> cancelBooking(@Valid @RequestBody final Booking booking) throws NoBookingException {
        bookingService.cancel(booking);

        return ResponseEntity.ok().build();
    }
}
