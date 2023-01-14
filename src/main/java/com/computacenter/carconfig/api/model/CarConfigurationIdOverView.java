package com.computacenter.carconfig.api.model;

import com.computacenter.carconfig.internal.EngineId;
import com.computacenter.carconfig.internal.PaintWorkId;
import com.computacenter.carconfig.internal.RimId;
import com.computacenter.carconfig.internal.SpecialEquipmentId;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import java.util.List;

@Builder
@Getter
public class CarConfigurationIdOverView {

    @Valid
    EngineId engineId;

    @Valid
    PaintWorkId paintWorkId;

    @Valid
    RimId rimsId;

    List<@Valid SpecialEquipmentId> specialEquipmentIds;
}
