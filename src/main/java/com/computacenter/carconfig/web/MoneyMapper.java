package com.computacenter.carconfig.web;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper
public interface MoneyMapper {
    default String mapMoney(Money money) {
        if (money != null) {
            return money.getAmount().toPlainString();
        }

        return null;
    }

    default Money mapMoney(BigDecimal amount) {
        if (amount != null) {
            return Money.of(CurrencyUnit.EUR, amount);
        }

        return null;
    }

    default String mapCurrencyUnit(CurrencyUnit currencyUnit) {
        if (currencyUnit != null) {
            return currencyUnit.getCode();
        }

        return null;
    }
}
