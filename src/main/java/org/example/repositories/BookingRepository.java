package org.example.repositories;

import org.example.exceptions.BookingNotFoundException;
import org.example.exceptions.PhoneAlreadyBookedException;
import org.example.model.Booking;
import org.example.model.Phone;
import org.example.model.User;

import java.util.Optional;
import java.util.Set;

public interface BookingRepository {

    Optional<Booking> findById(Integer id);

    Optional<Booking> findByPhoneAndUser(Phone phone, User user);

    Set<Booking> findAll();

    Booking add(Booking booking) throws PhoneAlreadyBookedException;

    void remove(Booking booking) throws BookingNotFoundException;
}
