package org.example.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder=true)
public class Booking {

    /*
    Only phone field is relevant for comparing bookings
    Getters are needed for serialization/deserialization
     */

    @Valid
    @NotNull
    private final Phone phone;

    @Valid
    @NotNull
    @EqualsAndHashCode.Exclude
    private final User user;

    @EqualsAndHashCode.Exclude
    private final LocalDateTime createdAt;

    public boolean isFor(final Phone phone, final User user) {
        return this.phone.equals(phone) && this.user.equals(user);
    }
}
