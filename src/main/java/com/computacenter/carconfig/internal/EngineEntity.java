package com.computacenter.carconfig.internal;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ENGINE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false, exclude = {"id", "carConfigurations"})
public class EngineEntity extends ManufacturedEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "ENGINE_ID", nullable = false, unique = true))
    private EngineId engineId;

    @Column(name = "HORSE_POWER", nullable = false)
    private Integer horsePower;

    @OneToMany(
            mappedBy = "engine",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CarConfigurationEntity> carConfigurations = new LinkedHashSet<>();
}
