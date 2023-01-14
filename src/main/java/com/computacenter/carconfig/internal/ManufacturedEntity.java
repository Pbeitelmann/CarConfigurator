package com.computacenter.carconfig.internal;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(exclude = {"manufacturer"})
public class ManufacturedEntity {
    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANUFACTURER_ID")
    private ManufacturerEntity manufacturer;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;
}
