package com.computacenter.carconfig.api.model;

import java.io.Serializable;

public interface BusinessEnum extends Serializable {

    /**
     * Returns the database value associated to this enum value
     *
     * @return the database value associated to this enum value
     */
    int getFixedOrdinal();

    /**
     * Returns the enum value associated to the given database value
     *
     * @param fixedOrdinal the database value associated to the enum value to get
     * @return the enum value associated to the given database value
     */
    Enum<?> getEnumLiteral(Integer fixedOrdinal);

}
