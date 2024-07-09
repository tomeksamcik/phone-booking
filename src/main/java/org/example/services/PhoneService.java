package org.example.services;

import org.example.model.Phone;

import java.util.Optional;
import java.util.Set;

public interface PhoneService {

    Optional<Phone> findById(Integer id);

    Set<Phone> findAll();
}
