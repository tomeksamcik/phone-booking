package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.model.Booking;
import org.example.model.Phone;
import org.example.model.User;
import org.example.services.exceptions.NoBookingException;
import org.example.services.exceptions.PhoneAlreadyBookedException;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class SimpleBookingService implements BookingService {

    private final Set<Booking> bookings;

    @Override
    public Booking book(Phone phone, User user) throws PhoneAlreadyBookedException {
        var booking = Booking.builder().phone(phone).user(user).build();
        if (bookings.add(booking)) {
            return booking;
        } else {
            throw new PhoneAlreadyBookedException();
        }
    }

    @Override
    public void cancel(Booking booking) throws NoBookingException {
        if (bookings.contains(booking)) {
            bookings.remove(booking);
        } else {
            throw new NoBookingException();
        }
    }

    @Override
    public Optional<Booking> findBooking(Phone phone) {
        return bookings.stream().filter(b -> b.isFor(phone)).findFirst();
    }
}
