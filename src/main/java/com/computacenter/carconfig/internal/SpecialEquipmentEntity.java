package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.EquipmentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SPECIAL_EQUIPMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false, exclude = {"id", "carConfigurations"})
public class SpecialEquipmentEntity extends ManufacturedEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "SPECIAL_EQUIPMENT_ID", nullable = false, unique = true))
    private SpecialEquipmentId specialEquipmentId;

    @Column(name = "EQUIPMENT_TYPE", nullable = false)
    @Convert(converter = EquipmentTypeConverter.class)
    private EquipmentType equipmentType;

    @ManyToMany(mappedBy = "specialEquipment")
    private List<CarConfigurationEntity> carConfigurations = new ArrayList<>();
}
