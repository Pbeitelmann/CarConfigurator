package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.model.SpecialEquipment;
import com.computacenter.carconfig.internal.ManufacturerIdMapper;
import com.computacenter.carconfig.internal.SpecialEquipmentIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {MoneyMapper.class, SpecialEquipmentIdMapper.class, ManufacturerIdMapper.class})
public interface SpecialEquipmentDataMapper {
    @Mapping(target = "currencyUnit", source = "price.currencyUnit")
    @Mapping(target = "manufacturerId", source = "manufacturer")
    SpecialEquipmentData toData(SpecialEquipment specialEquipment);
}
