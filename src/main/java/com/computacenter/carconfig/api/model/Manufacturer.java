package com.computacenter.carconfig.api.model;

import com.computacenter.carconfig.internal.ManufacturerId;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
@Getter
public class Manufacturer {
    @NotNull
    @Valid
    ManufacturerId manufacturerId;

    @NotBlank
    String name;

    @NotBlank
    @Email
    String contactEmail;

    @NotBlank
    String contactFirstName;

    @NotBlank
    String contactLastName;
}
