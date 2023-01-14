package com.computacenter.carconfig.api.model;

import com.computacenter.carconfig.internal.RimId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
public class Rim extends Manufactured {
    @Valid
    @NotNull
    RimId rimId;
}
