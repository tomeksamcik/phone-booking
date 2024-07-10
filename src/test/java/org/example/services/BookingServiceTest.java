package org.example.services;

import org.assertj.core.util.Sets;
import org.example.exceptions.BookingNotFoundException;
import org.example.exceptions.PhoneAlreadyBookedException;
import org.example.model.Booking;
import org.example.model.Phone;
import org.example.model.User;
import org.example.repositories.BookingRepository;
import org.example.repositories.PhoneRepository;
import org.example.repositories.SimpleBookingRepository;
import org.example.repositories.SimplePhoneRepository;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.model.PhoneNames.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingServiceTest {

    private final Set<Phone> phones =
            Set.of(Phone.builder().id(1).name(SAMSUNG_GALAXY_S9.label).build(),
                    Phone.builder().id(2).name(SAMSUNG_GALAXY_S8.label).build(),
                    Phone.builder().id(3).name(SAMSUNG_GALAXY_S8.label).build(),
                    Phone.builder().id(4).name(MOTOROLA_NEXUS_6.label).build(),
                    Phone.builder().id(5).name(ONEPLUS_9.label).build(),
                    Phone.builder().id(6).name(IPHONE_11.label).build(),
                    Phone.builder().id(7).name(IPHONE_12.label).build(),
                    Phone.builder().id(8).name(IPHONE_13.label).build(),
                    Phone.builder().id(9).name(IPHONE_X.label).build(),
                    Phone.builder().id(10).name(NOKIA_3310.label).build());

    private final BookingRepository bookingRepository = new SimpleBookingRepository(Sets.newHashSet());

    private final PhoneRepository phoneRepository = new SimplePhoneRepository(phones);

    private final BookingService bookingService = new BookingService(bookingRepository);

    private final PhoneService phoneService = new PhoneService(phoneRepository);

    @Test
    void shouldCreateBooking() throws PhoneAlreadyBookedException {
        var phone = phoneService.findById(2).get();
        var user = User.builder().firstName("Test").lastName("User").build();
        var booking = Booking.builder().phone(phone).user(user).build();

        bookingService.create(booking);
        var all = bookingService.findAll();

        assertThat(all).contains(booking);
    }

    @Test
    void shouldNotCreateBookingWhenCreatingBookingForTheSamePhoneTwice() throws PhoneAlreadyBookedException {
        var phone = phoneService.findById(1).get();
        var user = User.builder().firstName("Test").lastName("User").build();
        var otherUser = User.builder().firstName("Another").lastName("User").build();
        var bookingForUser = Booking.builder().phone(phone).user(user).build();
        var bookingForAnotherUser = Booking.builder().phone(phone).user(otherUser).build();

        bookingService.create(bookingForUser);

        assertThrows(PhoneAlreadyBookedException.class, () ->
                bookingService.create(bookingForAnotherUser));
    }

    @Test
    void shouldCancelBooking() throws BookingNotFoundException, PhoneAlreadyBookedException {
        var phone = phoneService.findById(1).get();
        var user = User.builder().firstName("Test").lastName("User").build();
        var booking = Booking.builder().phone(phone).user(user).build();

        var created = bookingService.create(booking);
        bookingService.cancel(created);
        var all = bookingService.findAll();

        assertThat(all).doesNotContain(booking);
    }

    @Test
    void shouldNotCancelBookingIfItsForAnotherUser() throws BookingNotFoundException, PhoneAlreadyBookedException {
        var phone = phoneService.findById(1).get();
        var user = User.builder().firstName("Test").lastName("User").build();
        var anotherUser = User.builder().firstName("Another").lastName("User").build();
        var bookingForUser = Booking.builder().phone(phone).user(user).build();
        var bookingForAnotherUser = Booking.builder().phone(phone).user(anotherUser).build();

        bookingService.create(bookingForUser);

        assertThrows(BookingNotFoundException.class, () ->
                bookingService.cancel(bookingForAnotherUser));
    }

    @Test
    void shouldNotCancelBookingWhenCancelingNonExistingBooking() {
        var phone = phoneService.findById(1).get();
        var booking = Booking.builder().phone(phone).build();

        assertThrows(BookingNotFoundException.class, () ->
                bookingService.cancel(booking));
    }
}
