package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.EquipmentType;
import jakarta.persistence.AttributeConverter;

public class EquipmentTypeConverter implements AttributeConverter<EquipmentType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EquipmentType billingType) {
        if (billingType == null) {
            return null;
        }

        return billingType.getFixedOrdinal();
    }

    @Override
    public EquipmentType convertToEntityAttribute(Integer databaseValue) {
        if (databaseValue == null) {
            return null;
        }

        return EquipmentType.AIR_CONDITIONER.getEnumLiteral(databaseValue);
    }

}
