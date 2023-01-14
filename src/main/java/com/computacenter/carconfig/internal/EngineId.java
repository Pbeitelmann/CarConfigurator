package com.computacenter.carconfig.internal;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class EngineId extends AbstractBusinessId {
    private EngineId(String id) {
        super(id);
    }

    public static EngineId of(String id) {
        return new EngineId(id);
    }

    @Override
    public String toString() {
        return "EngineId(" + super.toString() + ")";
    }
}
