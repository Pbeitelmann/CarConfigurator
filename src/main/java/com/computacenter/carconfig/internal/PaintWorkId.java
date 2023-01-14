package com.computacenter.carconfig.internal;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class PaintWorkId extends AbstractBusinessId {
    private PaintWorkId(String id) {
        super(id);
    }

    public static PaintWorkId of(String id) {
        return new PaintWorkId(id);
    }

    @Override
    public String toString() {
        return "PaintWorkId(" + super.toString() + ")";
    }
}
