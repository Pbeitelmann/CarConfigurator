package com.computacenter.carconfig.internal;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class RimId extends AbstractBusinessId {
    private RimId(String id) {
        super(id);
    }

    public static RimId of(String id) {
        return new RimId(id);
    }

    @Override
    public String toString() {
        return "RimId(" + super.toString() + ")";
    }
}
