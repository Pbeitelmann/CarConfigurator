package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.Rim;
import com.computacenter.carconfig.web.MoneyMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {MoneyMapper.class, ManufacturerEntityMapper.class})
interface RimEntityMapper {
    Rim toDomainModel(RimEntity rim);
}
