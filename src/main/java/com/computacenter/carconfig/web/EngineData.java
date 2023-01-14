package com.computacenter.carconfig.web;

import lombok.Data;

@Data
public class EngineData extends ManufacturedData {
    String engineId;
    String horsePower;
}
