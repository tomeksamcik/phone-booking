package org.example.services;

import org.example.model.Phone;

import java.util.Optional;

public interface PhoneService {

    Optional<Phone> findById(Integer id);
}
