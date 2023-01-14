package com.computacenter.carconfig.web;

import com.computacenter.carconfig.api.CarConfigurationService;
import com.computacenter.carconfig.internal.*;
import com.computacenter.carconfig.internal.exception.EngineNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@WebMvcTest(CarConfigurationController.class)
class CarConfigurationControllerIT {
    private final String API_BASE_PATH = "/cc-backend/api";
    private final String CREATE_CAR_CONFIGURATION_PATH = API_BASE_PATH + "/car/configuration/create";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarConfigurationService carConfigurationService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void createAceTimeRecordForUser_shouldCallService() throws Exception {
        var carConfigurationRequest = CarConfigurationRequest.builder()
                .engineId("EID-001")
                .paintWorkId("PID-001")
                .rimsId("RID-001")
                .specialEquipmentIds(List.of("SPID-001"))
                .build();

        String createCarConfigurationRequest = objectMapper.writeValueAsString(carConfigurationRequest);

        var configurationId = ConfigurationId.of("CID-001");
        when(carConfigurationService.createCarConfiguration(any())).thenReturn(configurationId);

        mockMvc.perform(post(CREATE_CAR_CONFIGURATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCarConfigurationRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(configurationId.getValue()));
    }

    @Test
    void createAceTimeRecordForUser_shouldReturnCorrectStatusAndErrorResponse() throws Exception {
        var carConfigurationRequest = CarConfigurationRequest.builder()
                .engineId("EID-001")
                .paintWorkId("PID-001")
                .rimsId("RID-001")
                .specialEquipmentIds(List.of("SPID-001"))
                .build();

        String createCarConfigurationRequest = objectMapper.writeValueAsString(carConfigurationRequest);

        var exceptionMessage = "Engine with EngineId 'EID-001', could not be found";
        when(carConfigurationService.createCarConfiguration(any())).thenThrow(new EngineNotFoundException(exceptionMessage));

        mockMvc.perform(post(CREATE_CAR_CONFIGURATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCarConfigurationRequest))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value(exceptionMessage));
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CarConfigurationRequestMapper carConfigurationRequestMapper() {
            return new CarConfigurationRequestMapperImpl();
        }

        @Bean
        public EngineIdMapper engineIdMapper() {
            return new EngineIdMapperImpl();
        }

        @Bean
        public PaintWorkIdMapper paintWorkIdMapper() {
            return new PaintWorkIdMapperImpl();
        }

        @Bean
        public RimIdMapper rimIdMapper() {
            return new RimIdMapperImpl();
        }

        @Bean
        public SpecialEquipmentIdMapper specialEquipmentIdMapper() {
            return new SpecialEquipmentIdMapperImpl();
        }

        @Bean
        public CarConfigurationViewResponseMapper carConfigurationViewResponseMapper() {
            return new CarConfigurationViewResponseMapperImpl();
        }

        @Bean
        public EngineDataMapper engineDataMapper() {
            return new EngineDataMapperImpl();
        }

        @Bean
        public RimDataMapper rimDataMapper() {
            return new RimDataMapperImpl();
        }

        @Bean
        public PaintWorkDataMapper paintWorkDataMapper() {
            return new PaintWorkDataMapperImpl();
        }

        @Bean
        public ManufacturerIdMapper manufacturerIdMapper() {
            return new ManufacturerIdMapperImpl();
        }

        @Bean
        public MoneyMapper moneyMapper() {
            return new MoneyMapperImpl();
        }

        @Bean
        public SpecialEquipmentDataMapper specialEquipmentDataMapper() {
            return new SpecialEquipmentDataMapperImpl();
        }
    }

}