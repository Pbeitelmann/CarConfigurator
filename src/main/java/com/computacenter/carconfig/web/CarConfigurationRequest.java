package com.computacenter.carconfig.web;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CarConfigurationRequest {
    String engineId;
    String paintWorkId;
    String rimsId;
    List<String> specialEquipmentIds;
}
