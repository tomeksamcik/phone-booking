package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.model.Booking;
import org.example.services.exceptions.NoBookingException;
import org.example.services.exceptions.PhoneAlreadyBookedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SimpleBookingService implements BookingService {

    private final Set<Booking> bookings;

    @Override
    public Booking create(final Booking booking) throws PhoneAlreadyBookedException {
        var timestamped = booking.toBuilder().createdAt(LocalDateTime.now()).build();
        /*
        Since only phone is used for comparing bookings, only one booking for the given phone is allowed
         */
        if (bookings.add(timestamped)) {
            return timestamped;
        } else {
            throw new PhoneAlreadyBookedException(String.format("Booking for the given phone has been already made (booking: %s)", timestamped));
        }
    }

    @Override
    public void cancel(final Booking booking) throws NoBookingException {
        /*
        Since only phone is used for comparing bookings, bookings.contains(booking) would ignore a user
         */
        if (bookings.stream().filter(b -> b.isFor(booking.getPhone(), booking.getUser())).findAny().isPresent()) {
            bookings.remove(booking);
        } else {
            throw new NoBookingException(String.format("No booking to cancel found (booking: %s)", booking));
        }
    }

    @Override
    public Set<Booking> findAll() {
        return bookings;
    }
}
