package com.computacenter.carconfig.api.model;

import com.computacenter.carconfig.internal.PaintWorkId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
public class PaintWork extends Manufactured {
    @Valid
    @NotNull
    PaintWorkId paintWorkId;

    @NotBlank
    String colorHexCode;
}
