package com.computacenter.carconfig.internal;

import static org.apache.logging.log4j.util.Strings.isBlank;
import org.mapstruct.Mapper;

@Mapper
public interface SpecialEquipmentIdMapper {
    default String mapId(SpecialEquipmentId specialEquipmentId) {
        if (specialEquipmentId != null) {
            return specialEquipmentId.getValue();
        }

        return null;
    }

    default SpecialEquipmentId mapId(String specialEquipmentId) {
        if (!isBlank(specialEquipmentId)) {
            return SpecialEquipmentId.of(specialEquipmentId);
        }

        return null;
    }
}
