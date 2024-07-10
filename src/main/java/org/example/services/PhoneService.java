package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.model.Phone;
import org.example.repositories.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public Optional<Phone> findById(final Integer id) {
        return phoneRepository.findById(id);
    }

    public Set<Phone> findAll() {
        return phoneRepository.findAll();
    }
}
