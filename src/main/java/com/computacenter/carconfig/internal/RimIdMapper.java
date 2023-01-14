package com.computacenter.carconfig.internal;

import static org.apache.logging.log4j.util.Strings.isBlank;
import org.mapstruct.Mapper;

@Mapper
public interface RimIdMapper {
    default String mapId(RimId rimId) {
        if (rimId != null) {
            return rimId.getValue();
        }

        return null;
    }

    default RimId mapId(String rimId) {
        if (!isBlank(rimId)) {
            return RimId.of(rimId);
        }

        return null;
    }
}
