package org.example;

import org.example.model.Phone;
import org.example.repositories.PhoneRepository;
import org.example.repositories.SimplePhoneRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

import static org.example.model.PhoneNames.*;

@Configuration
public class PhoneBookingConfiguration {

    @Bean
    public PhoneRepository init() {
        return new SimplePhoneRepository(Set.of(Phone.builder().id(1).name(SAMSUNG_GALAXY_S9.label).build(),
                Phone.builder().id(2).name(SAMSUNG_GALAXY_S8.label).build(),
                Phone.builder().id(3).name(SAMSUNG_GALAXY_S8.label).build(),
                Phone.builder().id(4).name(MOTOROLA_NEXUS_6.label).build(),
                Phone.builder().id(5).name(ONEPLUS_9.label).build(),
                Phone.builder().id(6).name(IPHONE_11.label).build(),
                Phone.builder().id(7).name(IPHONE_12.label).build(),
                Phone.builder().id(8).name(IPHONE_13.label).build(),
                Phone.builder().id(9).name(IPHONE_X.label).build(),
                Phone.builder().id(10).name(NOKIA_3310.label).build()));
    }
}
