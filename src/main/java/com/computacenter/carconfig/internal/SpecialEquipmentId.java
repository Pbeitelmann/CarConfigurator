package com.computacenter.carconfig.internal;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class SpecialEquipmentId extends AbstractBusinessId {
    private SpecialEquipmentId(String id) {
        super(id);
    }

    public static SpecialEquipmentId of(String id) {
        return new SpecialEquipmentId(id);
    }

    @Override
    public String toString() {
        return "SpecialEquipmentId(" + super.toString() + ")";
    }
}
