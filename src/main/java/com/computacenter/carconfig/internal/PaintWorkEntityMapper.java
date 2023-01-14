package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.PaintWork;
import com.computacenter.carconfig.web.MoneyMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {MoneyMapper.class})
interface PaintWorkEntityMapper {
    PaintWork toDomainModel(PaintWorkEntity paintWorkEntity);
}
