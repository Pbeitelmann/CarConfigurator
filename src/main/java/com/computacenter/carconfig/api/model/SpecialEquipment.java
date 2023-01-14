package com.computacenter.carconfig.api.model;

import com.computacenter.carconfig.internal.SpecialEquipmentId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
public class SpecialEquipment extends Manufactured {
    @Valid
    @NotNull
    SpecialEquipmentId specialEquipmentId;

    @NotNull
    EquipmentType equipmentType;
}
