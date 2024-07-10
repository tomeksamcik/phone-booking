package org.example.repositories;

import lombok.RequiredArgsConstructor;
import org.example.model.Phone;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class SimplePhoneRepository implements PhoneRepository {

    private final Set<Phone> phones;

    @Override
    public Optional<Phone> findById(Integer id) {
        return phones.stream().filter(p -> p.equalsId(id)).findFirst();
    }

    @Override
    public Set<Phone> findAll() {
        return phones;
    }
}
