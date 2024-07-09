package org.example.services;

import org.example.model.Booking;
import org.example.services.exceptions.NoBookingException;
import org.example.services.exceptions.PhoneAlreadyBookedException;

import java.util.Set;

public interface BookingService {

    Booking create(Booking booking) throws PhoneAlreadyBookedException;

    void cancel(Booking booking) throws NoBookingException;

    Set<Booking> findAll();
}
