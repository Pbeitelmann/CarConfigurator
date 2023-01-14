package com.computacenter.carconfig.web;

import com.computacenter.carconfig.internal.ConfigurationId;
import lombok.Data;

import java.util.List;

@Data
public class CarConfigurationViewResponse {
    ConfigurationId configurationId;
    String totalPrice;
    EngineData engineData;
    PaintWorkData paintWorkData;
    RimData rimData;
    List<SpecialEquipmentData> specialEquipmentData;
}
