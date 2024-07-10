package org.example.repositories;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.BookingNotFoundException;
import org.example.exceptions.PhoneAlreadyBookedException;
import org.example.model.Booking;
import org.example.model.Phone;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@RequiredArgsConstructor
public class SimpleBookingRepository implements BookingRepository {

    private final Set<Booking> bookings;

    private final AtomicInteger sequenceId = new AtomicInteger(0);

    @Override
    public Optional<Booking> findById(Integer id) {
        return bookings.stream().filter(b -> b.equalsId(id)).findFirst();
    }

    @Override
    public Optional<Booking> findByPhoneAndUser(Phone phone, User user) {
        return bookings.stream().filter(b -> b.isFor(phone, user)).findAny();
    }

    @Override
    public Set<Booking> findAll() {
        return bookings;
    }

    @Override
    public Booking add(Booking booking) throws PhoneAlreadyBookedException {
        var timestamped = booking.toBuilder().id(sequenceId.incrementAndGet()).createdAt(LocalDateTime.now()).build();
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
    public void remove(Booking booking) throws BookingNotFoundException {
        /*
        Since only phone is used for comparing bookings, bookings.contains(booking) would ignore a user
         */
        if (findByPhoneAndUser(booking.getPhone(), booking.getUser()).isPresent()) {
            bookings.remove(booking);
        } else {
            throw new BookingNotFoundException(String.format("No booking to cancel found (booking: %s)", booking));
        }
    }
}
