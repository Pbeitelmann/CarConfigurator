package com.computacenter.carconfig.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface RimRepository extends JpaRepository<RimEntity, Long> {
    @Query("SELECT r FROM RimEntity r " +
            "JOIN FETCH r.manufacturer")
    List<RimEntity> findAllWithManufacturer();

    @Query("SELECT r.name FROM RimEntity r WHERE " +
            "r.rimId = :rimId")
    String findNameByRimId(@Param("rimId") RimId rimId);

    Optional<RimEntity> findByRimId(RimId rimId);
}
