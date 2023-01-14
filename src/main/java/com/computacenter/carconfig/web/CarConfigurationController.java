package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.CarConfigurationService;
import com.computacenter.carconfig.internal.ConfigurationId;
import com.computacenter.carconfig.internal.OrderId;
import com.computacenter.carconfig.internal.UserId;
import static com.computacenter.carconfig.web.CarConfigurationController.API_BASE_PATH;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(API_BASE_PATH)
@RequiredArgsConstructor
@Validated
public class CarConfigurationController {
    public static final String API_BASE_PATH = "/cc-backend/api";

    private final CarConfigurationRequestMapper carConfigurationRequestMapper;
    private final CarConfigurationViewResponseMapper carConfigurationViewResponseMapper;
    private final EngineDataMapper engineDataMapper;
    private final PaintWorkDataMapper paintWorkDataMapper;
    private final RimDataMapper rimDataMapper;
    private final SpecialEquipmentDataMapper specialEquipmentDataMapper;

    private final CarConfigurationService carConfigurationService;

    @PostMapping("/car/configuration/create")
    public ResponseEntity<ConfigurationId> createCarConfiguration(@Valid @RequestBody CarConfigurationRequest request) {
        var carConfigurationIdOverview = carConfigurationRequestMapper.toConfiguration(request);
        var carConfigurationId = carConfigurationService.createCarConfiguration(carConfigurationIdOverview);

        return ResponseEntity.ok(carConfigurationId);
    }

    @PutMapping("/car/configuration/{configurationId}")
    public ResponseEntity<ConfigurationId> editCarConfiguration(@Valid @RequestBody CarConfigurationRequest request,
                                                                @PathVariable("configurationId") String configurationId) {
        var carConfiguration = carConfigurationRequestMapper.toConfiguration(request);
        var carConfigurationId = carConfigurationService.editCarConfiguration(ConfigurationId.of(configurationId), carConfiguration);

        return ResponseEntity.ok(carConfigurationId);
    }

    @GetMapping("/car/configuration/{configurationId}")
    public ResponseEntity<CarConfigurationViewResponse> getCarConfiguration(@PathVariable("configurationId") String configurationId) {
        var carConfigurationView = carConfigurationService.getCarConfiguration(ConfigurationId.of(configurationId));

        var carConfigurationViewResponse = carConfigurationViewResponseMapper.toResponse(carConfigurationView);

        return ResponseEntity.ok(carConfigurationViewResponse);
    }

    @GetMapping("/car/configuration/engine")
    public ResponseEntity<EngineResponse> getEngines() {
        var engineData = carConfigurationService.getEngines().stream()
                .map(engineDataMapper::toData)
                .toList();

        var response = EngineResponse.builder()
                .engines(engineData)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/car/configuration/paintwork")
    public ResponseEntity<PaintWorkResponse> getPaintWorks() {
        var paintWorkData = carConfigurationService.getPaintWorks().stream()
                .map(paintWorkDataMapper::toData)
                .toList();

        var response = PaintWorkResponse.builder()
                .paintWorks(paintWorkData)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/car/configuration/rims")
    public ResponseEntity<RimResponse> getRims() {
        var rimData = carConfigurationService.getRims().stream()
                .map(rimDataMapper::toData)
                .toList();

        var response = RimResponse.builder()
                .rims(rimData)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/car/configuration/special-equipment")
    public ResponseEntity<SpecialEquipmentResponse> getSpecialEquipment() {
        var specialEquipmentData = carConfigurationService.getSpecialEquipment().stream()
                .map(specialEquipmentDataMapper::toData)
                .toList();

        var response = SpecialEquipmentResponse.builder()
                .specialEquipment(specialEquipmentData)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/car/configuration/order")
    public ResponseEntity<OrderId> orderCarConfiguration(@Valid @RequestBody CarConfigurationOrderRequest request) {
        var userId = UserId.of(request.getUserId());
        var configurationId = ConfigurationId.of(request.getConfigurationId());

        var orderId = carConfigurationService.orderConfiguration(userId, configurationId);

        return ResponseEntity.ok(orderId);
    }
}
