package com.computacenter.carconfig.internal;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@NoArgsConstructor
public class OrderId extends AbstractBusinessId {
    private OrderId(String id) {
        super(id);
    }

    public static OrderId of(String id) {
        return new OrderId(id);
    }

    public static OrderId of(UUID uuid) {
        return new OrderId(uuid.toString());
    }

    @Override
    public String toString() {
        return "OrderId(" + super.toString() + ")";
    }
}
