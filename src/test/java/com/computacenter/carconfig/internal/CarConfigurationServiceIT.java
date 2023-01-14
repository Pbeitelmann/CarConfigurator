package com.computacenter.carconfig.internal;

import com.computacenter.carconfig.api.CarConfigurationService;
import com.computacenter.carconfig.api.model.CarConfigurationIdOverView;
import com.computacenter.carconfig.api.model.EquipmentType;
import com.computacenter.carconfig.internal.exception.EngineNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CarConfigurationServiceIT {

    @Autowired
    private CarConfigurationRepository carConfigurationRepository;

    @Autowired
    private EngineRepository engineRepository;

    @Autowired
    private PaintWorkRepository paintWorkRepository;

    @Autowired
    private RimRepository rimRepository;

    @Autowired
    private SpecialEquipmentRepository specialEquipmentRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private CarConfigurationService carConfigurationService;

    @Autowired
    private EngineEntityMapper engineEntityMapper;

    @Autowired
    private PaintWorkEntityMapper paintWorkEntityMapper;

    @Autowired
    private RimEntityMapper rimEntityMapper;

    @Autowired
    private SpecialEquipmentEntityMapper specialEquipmentEntityMapper;

    @BeforeEach
    void setUp() {
        engineRepository.deleteAll();
        rimRepository.deleteAll();
        specialEquipmentRepository.deleteAll();
        paintWorkRepository.deleteAll();
        manufacturerRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        engineRepository.deleteAll();
        rimRepository.deleteAll();
        specialEquipmentRepository.deleteAll();
        paintWorkRepository.deleteAll();
        manufacturerRepository.deleteAll();
    }

    @Test
    void createCarConfiguration_mustWork() {
        var manufacturerId = ManufacturerId.of("MAID-001");

        var manufacturer = ManufacturerEntity.builder()
                .manufacturerId(manufacturerId)
                .name("Audi")
                .contactEmail("contact@audi.de")
                .contactFirstName("Heinz")
                .contactLastName("Müller")
                .build();

        manufacturer = manufacturerRepository.save(manufacturer);

        var enginedId = EngineId.of("EID-001");
        var engineEntity = EngineEntity.builder()
                .engineId(enginedId)
                .horsePower(500)
                .name("Test Engine")
                .price(BigDecimal.TEN)
                .manufacturer(manufacturer)
                .build();

        engineEntity = engineRepository.save(engineEntity);

        var paintWorkId = PaintWorkId.of("PID-001");
        var paintWorkEntity = PaintWorkEntity.builder()
                .name("Midnight Blue")
                .price(BigDecimal.ONE)
                .paintWorkId(paintWorkId)
                .colorHexCode("#00000")
                .manufacturer(manufacturer)
                .build();

        paintWorkEntity = paintWorkRepository.save(paintWorkEntity);

        var rimId = RimId.of("RID-001");
        var rimEntity = RimEntity.builder()
                .rimId(rimId)
                .name("Turbo Spin")
                .price(BigDecimal.ONE)
                .manufacturer(manufacturer)
                .build();

        rimEntity = rimRepository.save(rimEntity);

        var specialEquipmentId = SpecialEquipmentId.of("SP-001");
        var specialEquipmentEntity = SpecialEquipmentEntity.builder()
                .specialEquipmentId(specialEquipmentId)
                .name("Airconditioner")
                .price(BigDecimal.TEN)
                .equipmentType(EquipmentType.AIR_CONDITIONER)
                .build();

        specialEquipmentEntity = specialEquipmentRepository.save(specialEquipmentEntity);


        var carConfigurationIdOverView = CarConfigurationIdOverView.builder()
                .engineId(enginedId)
                .paintWorkId(paintWorkId)
                .rimsId(rimId)
                .specialEquipmentIds(List.of(specialEquipmentId))
                .build();

        var configurationId = carConfigurationService.createCarConfiguration(carConfigurationIdOverView);

        Optional<CarConfigurationEntity> configurationOptional = carConfigurationRepository.findByConfigurationId(configurationId);
        assertThat(configurationOptional).isPresent();

        var carConfigurationEntity = configurationOptional.get();
        assertThat(carConfigurationEntity.getEngine()).isEqualTo(engineEntity);
        assertThat(carConfigurationEntity.getPaintWork()).isEqualTo(paintWorkEntity);
        assertThat(carConfigurationEntity.getRims()).isEqualTo(rimEntity);

        assertThat(carConfigurationEntity.getSpecialEquipment()).hasSize(1);
        assertThat(carConfigurationEntity.getSpecialEquipment().get(0)).isEqualTo(specialEquipmentEntity);
    }

    @Test
    void createCarConfiguration_shouldThrowEngineNotFoundException() {
        var manufacturerId = ManufacturerId.of("MAID-001");

        var manufacturer = ManufacturerEntity.builder()
                .manufacturerId(manufacturerId)
                .name("Audi")
                .contactEmail("contact@audi.de")
                .contactFirstName("Heinz")
                .contactLastName("Müller")
                .build();

        manufacturerRepository.save(manufacturer);

        var enginedId = EngineId.of("EID-001");
        var engineEntity = EngineEntity.builder()
                .engineId(enginedId)
                .horsePower(500)
                .name("Test Engine")
                .price(BigDecimal.TEN)
                .manufacturer(manufacturer)
                .build();

        engineRepository.save(engineEntity);

        var paintWorkId = PaintWorkId.of("PID-001");
        var paintWorkEntity = PaintWorkEntity.builder()
                .name("Midnight Blue")
                .price(BigDecimal.ONE)
                .paintWorkId(paintWorkId)
                .colorHexCode("#00000")
                .manufacturer(manufacturer)
                .build();

        paintWorkRepository.save(paintWorkEntity);

        var rimId = RimId.of("RID-001");
        var rimEntity = RimEntity.builder()
                .rimId(rimId)
                .name("Turbo Spin")
                .price(BigDecimal.ONE)
                .manufacturer(manufacturer)
                .build();

        rimRepository.save(rimEntity);

        var specialEquipmentId = SpecialEquipmentId.of("SP-001");
        var specialEquipmentEntity = SpecialEquipmentEntity.builder()
                .specialEquipmentId(specialEquipmentId)
                .name("Airconditioner")
                .price(BigDecimal.TEN)
                .equipmentType(EquipmentType.AIR_CONDITIONER)
                .build();

        specialEquipmentRepository.save(specialEquipmentEntity);

        var carConfigurationIdOverView = CarConfigurationIdOverView.builder()
                .engineId(EngineId.of("invalidId")) //invalidId
                .paintWorkId(paintWorkId)
                .rimsId(rimId)
                .specialEquipmentIds(List.of(specialEquipmentId))
                .build();

        assertThatExceptionOfType(EngineNotFoundException.class).isThrownBy(() ->
                carConfigurationService.createCarConfiguration(carConfigurationIdOverView));
    }


}