package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.model.EquipmentType;
import lombok.Data;

@Data
public class SpecialEquipmentData extends ManufacturedData {
    String specialEquipmentId;
    EquipmentType equipmentType;
}
