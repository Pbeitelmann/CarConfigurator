package com.computacenter.carconfig.internal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false, exclude = {"id", "carConfiguration"})
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "USER_ID", nullable = false))
    private UserId userId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "ORDER_ID", nullable = false, unique = true))
    private OrderId orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    private CarConfigurationEntity carConfiguration;
}
