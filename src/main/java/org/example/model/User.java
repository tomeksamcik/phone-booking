package org.example.model;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class User {

    private final String firstName;

    private final String lastName;
}
