package com.develhope.spring.deals.controllers;


import com.develhope.spring.vehicles.services.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void successfulOrderCreation_test() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/v1/orders/{userId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        "downPayment": true,
                                        "vehicleId": 1234,
                                        "userId": 5678,
                                        "adminId": null,
                                        "Vehicle": {
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
                                                     "usedFlag": "NEW",
                                                     "marketStatus": "AVAILABLE",
                                                     "discountFlag": true,
                                                     "engine": "4-cylinder"
                                                 },
                                        "User": {
                                                     "name": "Claudio",
                                                     "surname": "Genco",
                                                     "phoneNumber": 1241515,
                                                     "email": "genco.claudio@gmail.com",
                                                     "Roles": "ADMIN"
                                                 }
                                        }
                                        """))
                .andExpect(status().isCreated());

    }
}
