package com.computacenter.carconfig.api.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Builder
@Getter
public class CarConfiguration {
    @NotNull
    @Valid
    Engine engine;

    @NotNull
    @Valid
    PaintWork paintWork;

    @NotNull
    @Valid
    Rim rims;

    List<@Valid SpecialEquipment> specialEquipment;
}
