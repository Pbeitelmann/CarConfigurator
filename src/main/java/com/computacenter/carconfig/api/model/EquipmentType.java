package com.computacenter.carconfig.api.model;

import com.computacenter.carconfig.internal.EnumFixedOrdinalLiteralMapping;
import lombok.Getter;

@Getter
public enum EquipmentType implements BusinessEnum {
    AIR_CONDITIONER(0),
    SOUND_SYSTEM(1),
    DRIVING_SAFETY_SYSTEM(2),
    THEFT_SECURITY_SYSTEM(3);

    private static final EnumFixedOrdinalLiteralMapping<EquipmentType> fixedOrdinalLiteralMapping =
            new EnumFixedOrdinalLiteralMapping<>(AIR_CONDITIONER);

    private final int fixedOrdinal;

    EquipmentType(int fixedOrdinal) {
        this.fixedOrdinal = fixedOrdinal;
    }

    @Override
    public EquipmentType getEnumLiteral(Integer fixedOrdinal) {
        return fixedOrdinalLiteralMapping.getEnumLiteral(fixedOrdinal);
    }
}
