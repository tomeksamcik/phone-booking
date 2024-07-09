package org.example.rest;

import org.example.model.Booking;
import org.example.model.Phone;
import org.example.model.User;
import org.example.rest.exceptions.PhoneNotFoundException;
import org.example.services.BookingService;
import org.example.services.PhoneService;
import org.example.services.exceptions.NoBookingException;
import org.example.services.exceptions.PhoneAlreadyBookedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BookingController.class)
public class BookingControllerTest {

    @MockBean
    private PhoneService phoneService;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private BookingController bookingController;

    @Test
    void shouldGetAllBookings() {
        bookingController.getBookings();

        verify(bookingService, times(1)).findAll();
    }

    @Test
    void shouldCreateBooking() throws PhoneAlreadyBookedException, PhoneNotFoundException {
        var phone = Phone.builder().id(1).name("Test phone").build();
        var booking = Booking.builder().phone(phone).build();

        when(phoneService.findById(1)).thenReturn(Optional.of(phone));

        bookingController.createBooking(booking);

        verify(bookingService, times(1)).create(eq(booking));
    }

    @Test
    void shouldCreateBookingForInputWithNoPhoneName() throws PhoneAlreadyBookedException, PhoneNotFoundException {
        var inputPhoneWithNoName = Phone.builder().id(1).build();
        var phone = Phone.builder().id(1).name("Test phone").build();
        var user = User.builder().firstName("First").lastName("Last").build();
        var booking = Booking.builder().phone(inputPhoneWithNoName).user(user).build();
        var argument = ArgumentCaptor.forClass(Booking.class);

        when(phoneService.findById(1)).thenReturn(Optional.of(phone));

        bookingController.createBooking(booking);

        verify(bookingService, times(1)).create(argument.capture());

        assertThat(argument.getValue().getPhone().getName()).isEqualTo(phone.getName());
        assertThat(argument.getValue().getUser()).isEqualTo(user);
    }

    @Test
    void shouldNotCreateBookingForNonExistingPhone() {
        var phone = Phone.builder().id(2).build();
        var booking = Booking.builder().phone(phone).build();

        when(phoneService.findById(2)).thenReturn(Optional.empty());

        assertThrows(PhoneNotFoundException.class, () -> bookingController.createBooking(booking));
    }

    @Test
    void shouldNotCreateBookingForBookedPhone() throws PhoneAlreadyBookedException {
        var phone = Phone.builder().id(1).build();
        var booking = Booking.builder().phone(phone).build();

        when(phoneService.findById(1)).thenReturn(Optional.of(phone));
        when(bookingService.create(eq(booking))).thenThrow(new PhoneAlreadyBookedException("Phone is booked"));

        assertThrows(PhoneAlreadyBookedException.class, () -> bookingController.createBooking(booking));
    }

    @Test
    void shouldCancelBooking() throws NoBookingException {
        var phone = Phone.builder().id(1).name("Test phone").build();
        var booking = Booking.builder().phone(phone).build();

        bookingController.cancelBooking(booking);

        verify(bookingService, times(1)).cancel(eq(booking));
    }

    @Test
    void shouldNotCancelNonExistingBooking() throws NoBookingException {
        var phone = Phone.builder().id(1).name("Test phone").build();
        var booking = Booking.builder().phone(phone).build();

        doThrow(new NoBookingException("No such booking")).when(bookingService).cancel(eq(booking));

        assertThrows(NoBookingException.class, () -> bookingController.cancelBooking(booking));
    }
}
