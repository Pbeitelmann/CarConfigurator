package com.computacenter.carconfig.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface EngineRepository extends JpaRepository<EngineEntity, Long> {
    @Query("SELECT e FROM EngineEntity e " +
            "JOIN FETCH e.manufacturer")
    List<EngineEntity> findAllWithManufacturer();

    @Query("SELECT e.name FROM EngineEntity e " +
            "WHERE e.engineId = :engineId")
    String findNameByEngineId(@Param("engineId") EngineId engineId);

    Optional<EngineEntity> findByEngineId(EngineId engineId);
}
