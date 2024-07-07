package org.example.services;

import org.example.model.Phone;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.model.PhoneNames.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class SimplePhoneServiceTest {

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

    private final PhoneService phoneService = new SimplePhoneService(phones);

    @Test
    void shouldFindPhoneById() {
        var phone = phoneService.findById(9);

        assertThat(phone).isPresent();
        assertThat(phone.get()).isEqualTo(phones.stream().filter(p -> p.equalsId(9)).findFirst().get());
    }

    @Test
    void shouldNotFindNonExistingPhone() {
        var phone = phoneService.findById(99);

        assertThat(phone).isEmpty();
    }

    @Test
    void shouldNotInitializeWithPhonesWithDuplicatedId() {
        assertThrows(IllegalArgumentException.class, () ->
                Set.of(Phone.builder().id(1).name(SAMSUNG_GALAXY_S9.label).build(),
                        Phone.builder().id(1).name(SAMSUNG_GALAXY_S8.label).build()));
    }

    @Test
    void shouldInitializeWithPhonesWithDuplicatedName() {
        try {
            Set.of(Phone.builder().id(1).name(SAMSUNG_GALAXY_S9.label).build(),
                    Phone.builder().id(2).name(SAMSUNG_GALAXY_S9.label).build());
        } catch(Exception e) {
            fail();
        }
    }
}
