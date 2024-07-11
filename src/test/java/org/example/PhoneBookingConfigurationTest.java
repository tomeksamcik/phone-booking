package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PhoneBookingConfigurationTest {

    private final PhoneBookingConfiguration phoneBookingConfiguration = new PhoneBookingConfiguration();

    @Test
    void shouldCreatePhoneRepositoryInitializedWithPhones() {
        var phoneRepository = phoneBookingConfiguration.init();

        assertThat(phoneRepository.findAll().size()).isEqualTo(10);
    }
}
