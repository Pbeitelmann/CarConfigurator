package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.Manufacturer;
import org.mapstruct.Mapper;

@Mapper
interface ManufacturerEntityMapper {
    Manufacturer toDomainModel(ManufacturerEntity manufacturerEntity);
}
