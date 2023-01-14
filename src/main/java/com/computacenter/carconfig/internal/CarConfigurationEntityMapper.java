package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.CarConfigurationView;
import com.computacenter.carconfig.web.MoneyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CarConfigurationEntityMapper.class, MoneyMapper.class})
interface CarConfigurationEntityMapper {
    @Mapping(target = "carConfiguration", source = "carConfiguration")
    CarConfigurationView toView(CarConfigurationEntity carConfiguration);
}
