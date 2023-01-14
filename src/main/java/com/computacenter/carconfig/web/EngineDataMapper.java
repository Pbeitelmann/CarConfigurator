package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.model.Engine;
import com.computacenter.carconfig.internal.EngineIdMapper;
import com.computacenter.carconfig.internal.ManufacturerIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {MoneyMapper.class, EngineIdMapper.class, ManufacturerIdMapper.class})
public interface EngineDataMapper {
    @Mapping(target = "currencyUnit", source = "price.currencyUnit")
    @Mapping(target = "manufacturerId", source = "manufacturer")
    EngineData toData(Engine engine);
}
