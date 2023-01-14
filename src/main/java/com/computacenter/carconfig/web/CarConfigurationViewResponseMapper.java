package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.model.CarConfigurationView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {
        MoneyMapper.class,
        EngineDataMapper.class,
        PaintWorkDataMapper.class,
        RimDataMapper.class,
        SpecialEquipmentDataMapper.class
})
public interface CarConfigurationViewResponseMapper {
    @Mapping(target = "engineData", source = "carConfigurationView.carConfiguration.engine")
    @Mapping(target = "paintWorkData", source = "carConfigurationView.carConfiguration.paintWork")
    @Mapping(target = "rimData", source = "carConfigurationView.carConfiguration.rims")
    @Mapping(target = "specialEquipmentData", source = "carConfigurationView.carConfiguration.specialEquipment")
    CarConfigurationViewResponse toResponse(CarConfigurationView carConfigurationView);
}
