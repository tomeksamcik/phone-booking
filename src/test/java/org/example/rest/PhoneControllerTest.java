package org.example.rest;

import org.example.model.Phone;
import org.example.rest.exceptions.PhoneNotFoundException;
import org.example.services.PhoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PhoneController.class)
public class PhoneControllerTest {

    @MockBean
    private PhoneService phoneService;

    @Autowired
    private PhoneController phoneController;

    @Test
    void shouldGetAllPhones() {
        phoneController.getPhones();

        verify(phoneService, times(1)).findAll();
    }

    @Test
    void shouldGetPhoneById() throws PhoneNotFoundException {
        var phone = Phone.builder().id(1).name("Test phone").build();

        when(phoneService.findById(1)).thenReturn(Optional.of(phone));

        var found = phoneController.getPhoneById(1);

        assertThat(found).isEqualTo(phone);
    }

    @Test
    void shouldNotGetNonExistingPhoneById() throws PhoneNotFoundException {
        var phone = Phone.builder().id(1).name("Test phone").build();

        when(phoneService.findById(1)).thenReturn(Optional.of(phone));

        assertThrows(PhoneNotFoundException.class, () -> phoneController.getPhoneById(2));
    }
}
