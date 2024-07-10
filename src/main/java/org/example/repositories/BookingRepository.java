package org.example.repositories;

import org.example.model.Booking;
import org.example.model.Phone;
import org.example.model.User;

import java.util.Optional;
import java.util.Set;

public interface BookingRepository {

    Optional<Booking> findById(Integer id);

    Optional<Booking> findByPhoneAndUser(Phone phone, User user);

    Set<Booking> findAll();

    boolean add(Booking booking);

    void remove(Booking booking);

    Integer getNextSequenceId();
}
