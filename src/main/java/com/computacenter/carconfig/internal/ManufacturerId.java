package com.computacenter.carconfig.internal;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ManufacturerId extends AbstractBusinessId {
    private ManufacturerId(String id) {
        super(id);
    }

    public static ManufacturerId of(String id) {
        return new ManufacturerId(id);
    }

    @Override
    public String toString() {
        return "ManufacturerId(" + super.toString() + ")";
    }
}
