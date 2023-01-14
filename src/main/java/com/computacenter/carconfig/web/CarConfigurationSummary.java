package com.computacenter.carconfig.web;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder
public class CarConfigurationSummary {
    @NotBlank
    String engineName;

    @NotBlank
    String paintWorkName;

    @NotBlank
    String rimsName;

    @NotBlank
    List<String> specialEquipmentNames;

    @NotBlank
    String totalPrice;

    @NotBlank
    String currencyUnit;
}
