package com.computacenter.carconfig.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface PaintWorkRepository extends JpaRepository<PaintWorkEntity, Long> {
    @Query("SELECT p FROM PaintWorkEntity p " +
            "JOIN FETCH p.manufacturer")
    List<PaintWorkEntity> findAllWithManufacturer();

    @Query("SELECT p.name FROM PaintWorkEntity p WHERE " +
            "p.paintWorkId = :paintWorkId")
    String findNameByPaintWorkId(@Param("paintWorkId") PaintWorkId paintWorkId);

    Optional<PaintWorkEntity> findByPaintWorkId(PaintWorkId paintWorkId);
}
