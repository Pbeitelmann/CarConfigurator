package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.Engine;
import com.computacenter.carconfig.web.MoneyMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {MoneyMapper.class})
interface EngineEntityMapper {
    EngineEntity toEntity(Engine engine);

    Engine toDomainModel(EngineEntity engine);
}
