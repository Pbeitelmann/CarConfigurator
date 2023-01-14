package com.computacenter.carconfig.web;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PaintWorkResponse {
    List<PaintWorkData> paintWorks;
}
