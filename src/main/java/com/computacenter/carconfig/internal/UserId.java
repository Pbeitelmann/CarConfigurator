package com.computacenter.carconfig.internal;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class UserId extends AbstractBusinessId {
    private UserId(String id) {
        super(id);
    }

    public static UserId of(String id) {
        return new UserId(id);
    }

    @Override
    public String toString() {
        return "UserId(" + super.toString() + ")";
    }
}
