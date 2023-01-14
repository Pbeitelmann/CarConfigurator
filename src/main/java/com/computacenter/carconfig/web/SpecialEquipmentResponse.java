package com.computacenter.carconfig.web;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Builder
@Data
public class SpecialEquipmentResponse {
    List<@Valid SpecialEquipmentData> specialEquipment;
}
