package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.model.Phone;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class SimplePhoneService implements PhoneService {

    private final Set<Phone> phones;

    @Override
    public Optional<Phone> findById(final Integer id) {
        return phones.stream().filter(p -> p.equalsId(id)).findFirst();
    }

    @Override
    public Set<Phone> findAll() {
        return phones;
    }
}
