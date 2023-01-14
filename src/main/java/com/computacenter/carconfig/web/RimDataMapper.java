package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.model.Rim;
import com.computacenter.carconfig.internal.ManufacturerIdMapper;
import com.computacenter.carconfig.internal.RimIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {MoneyMapper.class, RimIdMapper.class, ManufacturerIdMapper.class})
public interface RimDataMapper {
    @Mapping(target = "currencyUnit", source = "price.currencyUnit")
    @Mapping(target = "manufacturerId", source = "manufacturer")
    RimData toData(Rim rim);
}
