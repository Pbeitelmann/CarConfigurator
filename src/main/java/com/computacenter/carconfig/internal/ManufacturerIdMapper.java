package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.Manufacturer;
import static org.apache.logging.log4j.util.Strings.isBlank;
import org.mapstruct.Mapper;

@Mapper
public interface ManufacturerIdMapper {
    default String toManufacturerId(Manufacturer manufacturer) {
        if (manufacturer != null) {
            return manufacturer.getManufacturerId().getValue();
        }

        return null;
    }

    default ManufacturerId toId(String manufacturerId) {
        if (!isBlank(manufacturerId)) {
            return ManufacturerId.of(manufacturerId);
        }

        return null;
    }
}
