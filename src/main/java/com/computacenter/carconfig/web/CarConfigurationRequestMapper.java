package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.model.CarConfigurationIdOverView;
import com.computacenter.carconfig.internal.*;
import org.mapstruct.Mapper;

@Mapper(uses = {
        EngineIdMapper.class,
        ManufacturerIdMapper.class,
        PaintWorkIdMapper.class,
        RimIdMapper.class,
        SpecialEquipmentIdMapper.class})
public interface CarConfigurationRequestMapper {
    CarConfigurationIdOverView toConfiguration(CarConfigurationRequest request);
}
