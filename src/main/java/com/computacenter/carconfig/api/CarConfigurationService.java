package com.computacenter.carconfig.api;

import com.computacenter.carconfig.api.model.*;
import com.computacenter.carconfig.internal.ConfigurationId;
import com.computacenter.carconfig.internal.OrderId;
import com.computacenter.carconfig.internal.SpecialEquipmentId;
import com.computacenter.carconfig.internal.UserId;
import com.computacenter.carconfig.web.CarConfigurationRequest;
import com.computacenter.carconfig.web.CarConfigurationSummary;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface CarConfigurationService {

    /**
     * Creates a CarConfiguration based on the supplied carConfigurationIdOverview
     *
     * @param carConfigurationIdOverview basis for CarConfiguration to create
     * @return id of created CarConfiguration
     */
    ConfigurationId createCarConfiguration(@Valid @NotNull CarConfigurationIdOverView carConfigurationIdOverview);

    /**
     * Edits the CarConfiguration identified by the supplied ConfigurationId based on the supplied carConfigurationIdOverview
     *
     * @param configurationId            configuration to edit
     * @param carConfigurationIdOverview editData
     * @return id of edited CarConfiguration
     */
    ConfigurationId editCarConfiguration(@Valid @NotNull ConfigurationId configurationId,
                                         @Valid @NotNull CarConfigurationIdOverView carConfigurationIdOverview);

    /**
     * Returns the CarConfiguration identified by the supplied ConfigurationId
     *
     * @param configurationId configuration to return
     * @return found configuration
     */
    CarConfigurationView getCarConfiguration(@Valid @NotNull ConfigurationId configurationId);

    /**
     * Returns all available Engines
     *
     * @return engines
     */
    List<Engine> getEngines();

    /**
     * Returns all available PaintWorks
     *
     * @return paintWorks
     */
    List<PaintWork> getPaintWorks();

    /**
     * Returns all available Rims
     *
     * @return rims
     */
    List<Rim> getRims();

    /**
     * Returns all available SpecialEquipment
     *
     * @return specialEquipment
     */
    List<SpecialEquipment> getSpecialEquipment();

    /**
     * Returns the SpecialEquipment identified by the supplied ids
     *
     * @param specialEquipmentIds SpecialEquipment to return
     * @return specialEquipment
     */
    List<SpecialEquipment> getSpecialEquipmentForSpecialEquipmentIds(List<SpecialEquipmentId> specialEquipmentIds);

    /**
     * Returns a CarConfigurationSummary for the supplied ConfigurationId
     *
     * @param configurationId configuration to check
     * @return carConfigurationSummary
     */
    CarConfigurationSummary getSummaryForConfigurationId(ConfigurationId configurationId);

    /**
     * Returns a CarConfigurationSummary for the supplied carConfigurationRequest
     *
     * @param carConfigurationRequest request identifying configuration to check
     * @return carConfigurationSummary
     */
    CarConfigurationSummary getSummaryForRequest(CarConfigurationRequest carConfigurationRequest);

    /**
     * Order the CarConfiguration identified by the supplied ConfigurationId for the supplied User
     *
     * @param userId          user to order configuration for
     * @param configurationId configuration to order
     * @return id of created order
     */
    OrderId orderConfiguration(@Valid @NotNull UserId userId,
                               @Valid @NotNull ConfigurationId configurationId);
}
