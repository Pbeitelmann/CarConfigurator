package com.computacenter.carconfig.internal;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PAINT_WORK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false, exclude = {"id", "carConfigurations"})
public class PaintWorkEntity extends ManufacturedEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "PAINT_WORK_ID", nullable = false, unique = true))
    private PaintWorkId paintWorkId;

    @Column(name = "COLOR_HEX_CODE", nullable = false)
    private String colorHexCode;

    @OneToMany(
            mappedBy = "paintWork",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CarConfigurationEntity> carConfigurations = new LinkedHashSet<>();
}
