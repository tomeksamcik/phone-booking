package org.example.services;

import org.example.model.Booking;
import org.example.exceptions.BookingNotFoundException;
import org.example.exceptions.PhoneAlreadyBookedException;

import java.util.Optional;
import java.util.Set;

public interface BookingService {

    Booking create(Booking booking) throws PhoneAlreadyBookedException;

    void cancel(Booking booking) throws BookingNotFoundException;

    Optional<Booking> findById(Integer id);

    Set<Booking> findAll();
}
