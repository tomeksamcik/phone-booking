package org.example.rest;

import lombok.AllArgsConstructor;
import org.example.model.Phone;
import org.example.rest.exceptions.PhoneNotFoundException;
import org.example.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("phones")
@AllArgsConstructor
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public Set<Phone> getPhones() {
        return phoneService.findAll();
    }

    @GetMapping("/{id}")
    public Phone getPhoneById(@PathVariable final Integer id) throws PhoneNotFoundException {
        return phoneService.findById(id).orElseThrow(() ->
                new PhoneNotFoundException(String.format("No phone found for the given id: %d", id)));
    }
}
