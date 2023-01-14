package com.computacenter.carconfig.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface SpecialEquipmentRepository extends JpaRepository<SpecialEquipmentEntity, Long> {
    @Query("SELECT s FROM SpecialEquipmentEntity s " +
            "JOIN FETCH s.manufacturer")
    List<SpecialEquipmentEntity> findAllWithManufacturer();

    @Query("SELECT s FROM SpecialEquipmentEntity s " +
            "JOIN FETCH s.manufacturer " +
            "WHERE s.specialEquipmentId IN :specialEquipmentIds")
    List<SpecialEquipmentEntity> findAllWithManufacturerWhereIdIn(@Param("specialEquipmentIds") List<SpecialEquipmentId> specialEquipmentIds);

    @Query("SELECT s.name FROM SpecialEquipmentEntity s WHERE " +
            "s.specialEquipmentId = :specialEquipmentId")
    String findNameBySpecialEquipmentId(@Param("specialEquipmentId") SpecialEquipmentId specialEquipmentId);

    Optional<SpecialEquipmentEntity> findBySpecialEquipmentId(SpecialEquipmentId specialEquipmentId);
}
