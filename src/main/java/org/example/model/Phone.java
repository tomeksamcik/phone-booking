package org.example.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class Phone {

    private final Integer id;

    @EqualsAndHashCode.Exclude
    private final String name;

    public boolean equalsId(Integer id) {
        return this.id.equals(id);
    }
}
