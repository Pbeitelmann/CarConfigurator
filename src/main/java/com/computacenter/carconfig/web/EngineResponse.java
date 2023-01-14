package com.computacenter.carconfig.web;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EngineResponse {
    List<EngineData> engines;
}
