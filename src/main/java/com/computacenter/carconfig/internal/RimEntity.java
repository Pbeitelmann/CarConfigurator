package com.computacenter.carconfig.internal;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "RIM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false, exclude = {"id", "carConfigurations"})
public class RimEntity extends ManufacturedEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "RIM_ID", nullable = false, unique = true))
    private RimId rimId;

    @OneToMany(
            mappedBy = "rims",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CarConfigurationEntity> carConfigurations = new LinkedHashSet<>();
}
