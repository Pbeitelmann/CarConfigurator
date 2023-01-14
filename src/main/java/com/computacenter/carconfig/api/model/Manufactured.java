package com.computacenter.carconfig.api.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.joda.money.Money;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
public class Manufactured {
    @NotBlank
    String name;

    @NotNull
    @Valid
    Manufacturer manufacturer;

    @NotNull
    @Valid
    Money price;
}
