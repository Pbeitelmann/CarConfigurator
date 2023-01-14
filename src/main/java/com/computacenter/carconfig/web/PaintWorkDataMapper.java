package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.model.PaintWork;
import com.computacenter.carconfig.internal.ManufacturerIdMapper;
import com.computacenter.carconfig.internal.PaintWorkIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {MoneyMapper.class, PaintWorkIdMapper.class, ManufacturerIdMapper.class})
public interface PaintWorkDataMapper {
    @Mapping(target = "currencyUnit", source = "price.currencyUnit")
    @Mapping(target = "manufacturerId", source = "manufacturer")
    PaintWorkData toData(PaintWork paintWork);
}
