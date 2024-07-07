package org.example.services;

import org.example.model.Booking;
import org.example.model.Phone;
import org.example.model.User;
import org.example.services.exceptions.NoBookingException;
import org.example.services.exceptions.PhoneAlreadyBookedException;

import java.util.Optional;

public interface BookingService {

    Booking book(Phone phone, User user) throws PhoneAlreadyBookedException;

    void cancel(Booking booking) throws NoBookingException;

    Optional<Booking> findBooking(Phone phone);
}
