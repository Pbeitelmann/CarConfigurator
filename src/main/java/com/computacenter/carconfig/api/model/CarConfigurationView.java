package com.computacenter.carconfig.api.model;

import com.computacenter.carconfig.internal.ConfigurationId;
import lombok.Builder;
import lombok.Getter;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
public class CarConfigurationView {

    @NotNull
    @Valid
    ConfigurationId configurationId;

    @NotNull
    @Valid
    CarConfiguration carConfiguration;

    @NotNull
    @Valid
    Money totalPrice;

    public void aggregateTotalPrice() {
        var specialEquipmentTotalAmount = this.carConfiguration.specialEquipment.stream()
                .map(equipment -> equipment.getPrice().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var configurationTotalAmount = specialEquipmentTotalAmount
                .add(this.carConfiguration.paintWork.getPrice().getAmount())
                .add(this.carConfiguration.getRims().getPrice().getAmount())
                .add(this.carConfiguration.engine.getPrice().getAmount());

        this.totalPrice = Money.of(CurrencyUnit.EUR, configurationTotalAmount);
    }
}
