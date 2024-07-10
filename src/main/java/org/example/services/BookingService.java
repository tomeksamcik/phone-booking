package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.BookingNotFoundException;
import org.example.exceptions.PhoneAlreadyBookedException;
import org.example.model.Booking;
import org.example.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking create(final Booking booking) throws PhoneAlreadyBookedException {
        return bookingRepository.add(booking);
    }

    public void cancel(final Booking booking) throws BookingNotFoundException {
        bookingRepository.remove(booking);
    }

    public Optional<Booking> findById(Integer id) {
        return bookingRepository.findById(id);
    }

    public Set<Booking> findAll() {
        return bookingRepository.findAll();
    }
}
