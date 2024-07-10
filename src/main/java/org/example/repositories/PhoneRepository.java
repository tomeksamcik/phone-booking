package org.example.repositories;

import org.example.model.Phone;

import java.util.Optional;
import java.util.Set;

public interface PhoneRepository {

    Optional<Phone> findById(Integer id);

    Set<Phone> findAll();
}
