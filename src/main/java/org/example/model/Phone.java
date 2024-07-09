package org.example.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Phone {

    /*
    Only id is relevant for comparing phones
    Getters are needed for serialization/deserialization
     */

    @NotNull
    private final Integer id;

    @EqualsAndHashCode.Exclude
    private final String name;

    public boolean equalsId(final Integer id) {
        return this.id.equals(id);
    }
}
