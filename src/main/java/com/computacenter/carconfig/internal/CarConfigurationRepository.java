package com.computacenter.carconfig.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface CarConfigurationRepository extends JpaRepository<CarConfigurationEntity, Long> {
    @Query("SELECT cc FROM CarConfigurationEntity cc " +
            "JOIN FETCH cc.engine " +
            "JOIN FETCH cc.paintWork " +
            "JOIN FETCH cc.rims " +
            "JOIN FETCH cc.specialEquipment " +
            "WHERE cc.configurationId = :configurationId")
    Optional<CarConfigurationEntity> findByConfigurationId(@Param("configurationId") ConfigurationId configurationId);
}
