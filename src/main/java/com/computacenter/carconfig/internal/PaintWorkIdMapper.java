package com.computacenter.carconfig.internal;

import static org.apache.logging.log4j.util.Strings.isBlank;
import org.mapstruct.Mapper;

@Mapper
public interface PaintWorkIdMapper {
    default String mapId(PaintWorkId paintWorkId) {
        if (paintWorkId != null) {
            return paintWorkId.getValue();
        }

        return null;
    }

    default PaintWorkId mapId(String paintWorkId) {
        if (!isBlank(paintWorkId)) {
            return PaintWorkId.of(paintWorkId);
        }

        return null;
    }
}
