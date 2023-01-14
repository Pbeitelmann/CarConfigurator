package com.computacenter.carconfig.internal;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@NoArgsConstructor
public class ConfigurationId extends AbstractBusinessId {
    private ConfigurationId(String id) {
        super(id);
    }

    public static ConfigurationId of(String id) {
        return new ConfigurationId(id);
    }

    public static ConfigurationId of(UUID uuid) {
        return new ConfigurationId(uuid.toString());
    }

    @Override
    public String toString() {
        return "ConfigurationId(" + super.toString() + ")";
    }
}
