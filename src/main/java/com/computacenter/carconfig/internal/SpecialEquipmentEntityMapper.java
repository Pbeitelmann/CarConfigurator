package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.SpecialEquipment;
import com.computacenter.carconfig.web.MoneyMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {MoneyMapper.class})
interface SpecialEquipmentEntityMapper {
    SpecialEquipment toDomainModel(SpecialEquipmentEntity specialEquipment);
}
