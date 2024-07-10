package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.BookingNotFoundException;
import org.example.exceptions.PhoneAlreadyBookedException;
import org.example.model.Booking;
import org.example.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking create(final Booking booking) throws PhoneAlreadyBookedException {
        var timestamped = booking.toBuilder().id(bookingRepository.getNextSequenceId()).createdAt(LocalDateTime.now()).build();
        /*
        Since only phone is used for comparing bookings, only one booking for the given phone is allowed
         */
        if (bookingRepository.add(timestamped)) {
            return timestamped;
        } else {
            throw new PhoneAlreadyBookedException(String.format("Booking for the given phone has been already made (booking: %s)", timestamped));
        }
    }

    public void cancel(final Booking booking) throws BookingNotFoundException {
        /*
        Since only phone is used for comparing bookings, bookings.contains(booking) would ignore a user
         */
        if (bookingRepository.findByPhoneAndUser(booking.getPhone(), booking.getUser()).isPresent()) {
            bookingRepository.remove(booking);
        } else {
            throw new BookingNotFoundException(String.format("No booking to cancel found (booking: %s)", booking));
        }
    }

    public Optional<Booking> findById(Integer id) {
        return bookingRepository.findById(id);
    }

    public Set<Booking> findAll() {
        return bookingRepository.findAll();
    }
}
