package com.computacenter.carconfig.internal;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class AbstractBusinessId implements Serializable, Comparable<AbstractBusinessId> {

    @NotBlank
    @Size(max = 50)
    private String value;

    protected AbstractBusinessId(@NotBlank @Size(max = 50) String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBusinessId that = (AbstractBusinessId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(AbstractBusinessId that) {
        if (this == that) return 0;
        return this.value.compareTo(that.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
