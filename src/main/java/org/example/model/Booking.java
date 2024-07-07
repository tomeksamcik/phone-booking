package org.example.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@ToString
@EqualsAndHashCode
public class Booking {

    private final Phone phone;

    @EqualsAndHashCode.Exclude
    private final User user;

    @EqualsAndHashCode.Exclude
    private final LocalDate createdAt = LocalDate.now();

    public boolean isFor(Phone phone) {
        return this.phone.equals(phone);
    }
}
