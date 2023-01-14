package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.model.BusinessEnum;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.Map;

public class EnumFixedOrdinalLiteralMapping<T extends Enum<T> & BusinessEnum> {

    private final Map<Integer, T> fixedOrdinalEnumLiteralsMap;

    public EnumFixedOrdinalLiteralMapping(T enumLiteral) {
        T[] enumConstants = enumLiteral.getDeclaringClass().getEnumConstants();
        Map<Integer, T> fixedOrdinalEnumLiteralsMap = new HashMap<>();
        for (T enumConstant : enumConstants) {
            fixedOrdinalEnumLiteralsMap.put(enumConstant.getFixedOrdinal(), enumConstant);
        }
        this.fixedOrdinalEnumLiteralsMap = unmodifiableMap(fixedOrdinalEnumLiteralsMap);
    }

    public T getEnumLiteral(Integer fixedOrdinal) {
        T result = fixedOrdinalEnumLiteralsMap.get(fixedOrdinal);
        if (result == null) {
            throw new IllegalStateException(format("No enum value defined for given database value '%d", fixedOrdinal));
        }
        return result;
    }

    public Map<Integer, T> getFixedOrdinalEnumLiteralsMap() {
        return this.fixedOrdinalEnumLiteralsMap;
    }
}
