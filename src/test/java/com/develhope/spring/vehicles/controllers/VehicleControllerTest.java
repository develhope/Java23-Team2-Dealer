package com.develhope.spring.vehicles.controllers;

import com.develhope.spring.vehicles.dtos.VehicleCreateDTO;
import com.develhope.spring.vehicles.dtos.VehicleDTO;
import com.develhope.spring.vehicles.services.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createVehicle() throws Exception {
        String requestBody = """
            {
                "type": "VAN",
                "brand": "Fiat",
                "model": "Fiorino",
                "displacement": 1200,
                "color": "WHITE",
                "power": 70,
                "gear": "MANUAL",
                "registrationYear": 2022,
                "powerSupply": "METHANE",
                "originalPrice": 15000,
                "discountedPrice": 14000,
                "usedFlag": "NEW",
                "marketStatus": "AVAILABLE",
                "discountFlag": true,
                "engine": "4-cylinder"
            }
            """;

        when(vehicleService.create(any(Long.class), any(VehicleCreateDTO.class)))
                .thenReturn(new VehicleDTO());

        mockMvc.perform(
                        post("/vehicles/{userId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isCreated());
    }
}