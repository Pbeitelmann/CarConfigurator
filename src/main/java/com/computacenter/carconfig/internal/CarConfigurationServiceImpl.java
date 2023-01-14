package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.CarConfigurationService;
import com.computacenter.carconfig.api.model.*;
import com.computacenter.carconfig.internal.exception.*;
import com.computacenter.carconfig.web.CarConfigurationRequest;
import com.computacenter.carconfig.web.CarConfigurationSummary;
import static java.lang.String.format;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Validated
@Service
public class CarConfigurationServiceImpl implements CarConfigurationService {

    private final CarConfigurationRepository carConfigurationRepository;
    private final EngineRepository engineRepository;
    private final PaintWorkRepository paintWorkRepository;
    private final RimRepository rimRepository;
    private final SpecialEquipmentRepository specialEquipmentRepository;
    private final OrderRepository orderRepository;

    private final CarConfigurationEntityMapper carConfigurationEntityMapper;
    private final EngineEntityMapper engineEntityMapper;
    private final PaintWorkEntityMapper paintworkEntityMapper;
    private final RimEntityMapper rimEntityMapper;
    private final SpecialEquipmentEntityMapper specialEquipmentEntityMapper;

    @Transactional
    @Override
    public ConfigurationId createCarConfiguration(CarConfigurationIdOverView carConfigurationIdOverView) {
        var configurationEntity = new CarConfigurationEntity();
        configurationEntity.setConfigurationId(ConfigurationId.of(UUID.randomUUID()));

        mapCarConfigurationEntity(configurationEntity, carConfigurationIdOverView);

        configurationEntity = carConfigurationRepository.save(configurationEntity);

        return configurationEntity.getConfigurationId();
    }

    @Transactional
    @Override
    public ConfigurationId editCarConfiguration(ConfigurationId configurationId, CarConfigurationIdOverView carConfigurationIdOverView) {
        var carConfigurationEntity = findCarConfiguration(configurationId);
        mapCarConfigurationEntity(carConfigurationEntity, carConfigurationIdOverView);

        carConfigurationEntity = carConfigurationRepository.save(carConfigurationEntity);

        return carConfigurationEntity.getConfigurationId();
    }

    @Override
    public CarConfigurationView getCarConfiguration(ConfigurationId configurationId) {
        var carConfigurationEntity = findCarConfiguration(configurationId);
        var carConfigurationView = carConfigurationEntityMapper.toView(carConfigurationEntity);

        carConfigurationView.aggregateTotalPrice();

        return carConfigurationView;
    }

    @Override
    public List<Engine> getEngines() {
        return engineRepository.findAllWithManufacturer().stream()
                .map(engineEntityMapper::toDomainModel)
                .toList();
    }

    @Override
    public List<PaintWork> getPaintWorks() {
        return paintWorkRepository.findAllWithManufacturer().stream()
                .map(paintworkEntityMapper::toDomainModel)
                .toList();
    }

    @Override
    public List<Rim> getRims() {
        return rimRepository.findAllWithManufacturer().stream()
                .map(rimEntityMapper::toDomainModel)
                .toList();
    }

    @Override
    public List<SpecialEquipment> getSpecialEquipment() {
        return specialEquipmentRepository.findAllWithManufacturer().stream()
                .map(specialEquipmentEntityMapper::toDomainModel)
                .toList();
    }

    @Override
    public List<SpecialEquipment> getSpecialEquipmentForSpecialEquipmentIds(List<SpecialEquipmentId> specialEquipmentIds) {
        return specialEquipmentRepository.findAllWithManufacturerWhereIdIn(specialEquipmentIds).stream()
                .map(specialEquipmentEntityMapper::toDomainModel)
                .toList();
    }

    @Override
    public CarConfigurationSummary getSummaryForConfigurationId(ConfigurationId configurationId) {

        var carConfigurationView = getCarConfiguration(configurationId);
        var carConfiguration = carConfigurationView.getCarConfiguration();
        var specialEquipmentNames = carConfiguration.getSpecialEquipment().stream()
                .map(Manufactured::getName)
                .toList();

        carConfigurationView.aggregateTotalPrice();

        return CarConfigurationSummary.builder()
                .engineName(carConfiguration.getEngine().getName())
                .paintWorkName(carConfiguration.getPaintWork().getName())
                .rimsName(carConfiguration.getRims().getName())
                .specialEquipmentNames(specialEquipmentNames)
                .totalPrice(carConfigurationView.getTotalPrice().getAmount().toPlainString())
                .build();
    }

    @Override
    public CarConfigurationSummary getSummaryForRequest(CarConfigurationRequest carConfigurationRequest) {
        var engineId = EngineId.of(carConfigurationRequest.getEngineId());
        var paintWorkId = PaintWorkId.of(carConfigurationRequest.getPaintWorkId());
        var rimId = RimId.of(carConfigurationRequest.getRimsId());

        var engine = engineRepository.findByEngineId(engineId).orElseThrow(() ->
                createEngineNotFoundException(engineId));

        var paintWork = paintWorkRepository.findByPaintWorkId(paintWorkId).orElseThrow(() ->
                createPaintWorkNotFoundException(paintWorkId));

        var rims = rimRepository.findByRimId(rimId).orElseThrow(() ->
                createRimNotFoundException(rimId));

        var specialEquipments = carConfigurationRequest.getSpecialEquipmentIds().stream()
                .map(SpecialEquipmentId::of)
                .map(id -> specialEquipmentRepository.findBySpecialEquipmentId(id).orElseThrow(() ->
                        createSpecialEquipmentNotFoundException(id)))
                .toList();

        var specialEquipmentNames = specialEquipments.stream()
                .map(SpecialEquipmentEntity::getName)
                .toList();

        var specialEquipmentTotalAmount = specialEquipments.stream()
                .map(ManufacturedEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var configurationTotalAmount = specialEquipmentTotalAmount
                .add(paintWork.getPrice())
                .add(rims.getPrice())
                .add(engine.getPrice());

        return CarConfigurationSummary.builder()
                .engineName(engine.getName())
                .paintWorkName(paintWork.getName())
                .rimsName(rims.getName())
                .specialEquipmentNames(specialEquipmentNames)
                .totalPrice(configurationTotalAmount.toPlainString())
                .build();
    }

    @Transactional
    @Override
    public OrderId orderConfiguration(UserId userId, ConfigurationId configurationId) {
        var carConfigurationEntity = findCarConfiguration(configurationId);

        var orderEntity = OrderEntity.builder()
                .userId(userId)
                .orderId(OrderId.of(UUID.randomUUID()))
                .carConfiguration(carConfigurationEntity)
                .build();

        orderRepository.save(orderEntity);

        return orderEntity.getOrderId();
    }

    private void mapCarConfigurationEntity(CarConfigurationEntity carConfigurationEntity, CarConfigurationIdOverView idOverView) {
        if (idOverView.getEngineId() != null) {
            carConfigurationEntity.setEngine(findEngine(idOverView.getEngineId()));
        }

        if (idOverView.getPaintWorkId() != null) {
            carConfigurationEntity.setPaintWork(findPaintWork(idOverView.getPaintWorkId()));
        }

        if (idOverView.getRimsId() != null) {
            carConfigurationEntity.setRims(findRims(idOverView.getRimsId()));
        }

        if (idOverView.getSpecialEquipmentIds() != null) {
            carConfigurationEntity.setSpecialEquipment(findSpecialEquipment(idOverView.getSpecialEquipmentIds()));
        }
    }

    private CarConfigurationEntity findCarConfiguration(ConfigurationId configurationId) {
        return carConfigurationRepository.findByConfigurationId(configurationId).orElseThrow(() ->
                createCarConfigurationNotFoundException(configurationId));
    }

    private EngineEntity findEngine(EngineId engineId) {
        return engineRepository.findByEngineId(engineId).orElseThrow(() ->
                createEngineNotFoundException(engineId));
    }

    private PaintWorkEntity findPaintWork(PaintWorkId paintWorkId) {
        return paintWorkRepository.findByPaintWorkId(paintWorkId).orElseThrow(() ->
                createPaintWorkNotFoundException(paintWorkId));
    }

    private RimEntity findRims(RimId rimId) {
        return rimRepository.findByRimId(rimId).orElseThrow(() ->
                createRimNotFoundException(rimId));
    }

    private List<SpecialEquipmentEntity> findSpecialEquipment(List<SpecialEquipmentId> specialEquipmentIds) {
        return specialEquipmentIds.stream()
                .map(id -> specialEquipmentRepository.findBySpecialEquipmentId(id)
                        .orElseThrow(() -> createSpecialEquipmentNotFoundException(id)))
                .toList();
    }

    CarConfigurationNotFoundException createCarConfigurationNotFoundException(ConfigurationId configurationId) {
        return new CarConfigurationNotFoundException(format("CarConfiguration with ConfigurationId '%s' could not be found", configurationId.getValue()));
    }

    EngineNotFoundException createEngineNotFoundException(EngineId engineId) {
        return new EngineNotFoundException(format("Engine with EngineId '%s' could not be found", engineId.getValue()));
    }

    PaintWorkNotFoundException createPaintWorkNotFoundException(PaintWorkId paintWorkId) {
        return new PaintWorkNotFoundException(format("PaintWork with PaintWorkId '%s' could not be found", paintWorkId.getValue()));
    }

    RimNotFoundException createRimNotFoundException(RimId rimId) {
        return new RimNotFoundException(format("Rim with RimId '%s' could not be found", rimId.getValue()));
    }

    SpecialEquipmentNotFoundException createSpecialEquipmentNotFoundException(SpecialEquipmentId specialEquipmentId) {
        return new SpecialEquipmentNotFoundException(format("SpecialEquipment with SpecialEquipmentId '%s' could not be found", specialEquipmentId.getValue()));
    }
}
