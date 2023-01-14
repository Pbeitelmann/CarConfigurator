package com.computacenter.carconfig.internal;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CAR_CONFIGURATION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"id", "orders"})
public class CarConfigurationEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "CONFIGURATION_ID", nullable = false, unique = true))
    private ConfigurationId configurationId;

    @ManyToOne(fetch = FetchType.LAZY)
    private EngineEntity engine;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaintWorkEntity paintWork;

    @ManyToOne(fetch = FetchType.LAZY)
    private RimEntity rims;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "CONFIGURATION_SPECIAL_EQUIPMENT",
            joinColumns = @JoinColumn(name = "configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "special_equipment_id")
    )
    private List<SpecialEquipmentEntity> specialEquipment = new ArrayList<>();

    @OneToMany(
            mappedBy = "carConfiguration",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderEntity> orders = new ArrayList<>();

    public void addOrder(OrderEntity order) {
        orders.add(order);
        order.setCarConfiguration(this);
    }

    public void removeOrder(OrderEntity order) {
        orders.remove(order);
        order.setCarConfiguration(null);
    }
}
