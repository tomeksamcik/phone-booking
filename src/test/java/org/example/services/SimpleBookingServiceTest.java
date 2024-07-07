package org.example.services;

import org.assertj.core.util.Sets;
import org.example.model.Booking;
import org.example.model.Phone;
import org.example.model.User;
import org.example.services.exceptions.NoBookingException;
import org.example.services.exceptions.PhoneAlreadyBookedException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.model.PhoneNames.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleBookingServiceTest {

    private final Set<Phone> phones = Set.of(Phone.builder().id(1).name(SAMSUNG_GALAXY_S9.label).build(),
            Phone.builder().id(2).name(SAMSUNG_GALAXY_S8.label).build(),
            Phone.builder().id(3).name(SAMSUNG_GALAXY_S8.label).build(),
            Phone.builder().id(4).name(MOTOROLA_NEXUS_6.label).build(),
            Phone.builder().id(5).name(ONEPLUS_9.label).build(),
            Phone.builder().id(6).name(IPHONE_11.label).build(),
            Phone.builder().id(7).name(IPHONE_12.label).build(),
            Phone.builder().id(8).name(IPHONE_13.label).build(),
            Phone.builder().id(9).name(IPHONE_X.label).build(),
            Phone.builder().id(10).name(NOKIA_3310.label).build());

    private final BookingService bookingService = new SimpleBookingService(Sets.newHashSet());

    private final PhoneService phoneService = new SimplePhoneService(phones);

    @Test
    void shouldBookPhone() throws PhoneAlreadyBookedException {
        var phone = phoneService.findById(2).get();
        var anotherPhone = phoneService.findById(3).get();
        var user = User.builder().firstName("Test").lastName("User").build();

        bookingService.book(phone, user);
        var booking = bookingService.findBooking(phone);

        assertThat(booking).isPresent();
        assertThat(booking.get().isFor(phone)).isTrue();
        assertThat(booking.get().isFor(anotherPhone)).isFalse();
    }

    @Test
    void shouldThrowAnExceptionWhenBookingTheSamePhoneTwice() throws PhoneAlreadyBookedException {
        var phone = phoneService.findById(1).get();
        var user = User.builder().firstName("Test").lastName("User").build();

        bookingService.book(phone, user);

        assertThrows(PhoneAlreadyBookedException.class, () ->
                bookingService.book(phone, user));
    }

    @Test
    void shouldCancelBookedPhone() throws NoBookingException, PhoneAlreadyBookedException {
        var phone = phoneService.findById(1).get();
        var user = User.builder().firstName("Test").lastName("User").build();

        var booking = bookingService.book(phone, user);
        bookingService.cancel(booking);
        var found = bookingService.findBooking(phone);

        assertThat(found).isEmpty();
    }

    @Test
    void shouldThrowAnExceptionWhenCancelingNonExistingBooking() {
        var phone = phoneService.findById(1).get();
        var booking = Booking.builder().phone(phone).build();

        assertThrows(NoBookingException.class, () ->
                bookingService.cancel(booking));
    }
}
