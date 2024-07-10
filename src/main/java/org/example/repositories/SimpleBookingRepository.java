package org.example.repositories;

import lombok.RequiredArgsConstructor;
import org.example.model.Booking;
import org.example.model.Phone;
import org.example.model.User;
import org.springframework.stereotype.Repository;

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
    public boolean add(Booking booking) {
        return bookings.add(booking);
    }

    @Override
    public void remove(Booking booking) {
        bookings.remove(booking);
    }

    @Override
    public Integer getNextSequenceId() {
        return sequenceId.incrementAndGet();
    }
}
