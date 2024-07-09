package org.example.services;

import org.example.model.Booking;
import org.example.exceptions.NoBookingException;
import org.example.exceptions.PhoneAlreadyBookedException;

import java.util.Optional;
import java.util.Set;

public interface BookingService {

    Booking create(Booking booking) throws PhoneAlreadyBookedException;

    void cancel(Booking booking) throws NoBookingException;

    Optional<Booking> findById(Integer id);

    Set<Booking> findAll();
}
