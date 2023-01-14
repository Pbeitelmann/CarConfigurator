package com.computacenter.carconfig.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface ManufacturerRepository extends JpaRepository<ManufacturerEntity, Long> {
    @Query("SELECT mf.manufacturerId FROM ManufacturerEntity mf " +
            "WHERE mf.id = :manufacturerIdFk")
    Optional<ManufacturerId> findManufacturerIdByFk(@Param("manufacturerIdFk") Long manufacturerIdFk);
}
