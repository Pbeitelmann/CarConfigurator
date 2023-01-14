package com.computacenter.carconfig.internal;

import static org.apache.logging.log4j.util.Strings.isBlank;
import org.mapstruct.Mapper;

@Mapper
public interface EngineIdMapper {
    default String mapId(EngineId engineId) {
        if (engineId != null) {
            return engineId.getValue();
        }

        return null;
    }

    default EngineId mapId(String engineId) {
        if (!isBlank(engineId)) {
            return EngineId.of(engineId);
        }

        return null;
    }
}
