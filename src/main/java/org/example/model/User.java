package org.example.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class User {

    /*
    Getters are needed for serialization/deserialization
     */

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;
}
