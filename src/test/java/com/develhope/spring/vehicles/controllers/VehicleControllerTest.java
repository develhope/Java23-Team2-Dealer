package com.develhope.spring.vehicles.controllers;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.vehicles.services.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.develhope.spring.users.models.Roles.ADMIN;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class VehicleControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private VehicleService vehicleService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void successfulVehicleCreation_test() throws Exception {
//        this.mockMvc.perform(
//                        MockMvcRequestBuilders.post("/v1/vehicles/{userId}", 1L)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content("""
//                                        {
//                                            "type": "VAN",
//                                            "brand": "Fiat",
//                                            "model": "Fiorino",
//                                            "displacement": 1200,
//                                            "color": "WHITE",
//                                            "power": 70,
//                                            "gear": "MANUAL",
//                                            "registrationYear": 2022,
//                                            "powerSupply": "METHANE",
//                                            "originalPrice": 15000,
//                                            "usedFlag": "NEW",
//                                            "marketStatus": "AVAILABLE",
//                                            "discountFlag": true,
//                                            "engine": "4-cylinder"
//                                        }
//                                        """))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void successfulVehicleModification_test() throws Exception {
//        Roles roles = ADMIN;
//        long userId = 1L;
//        long vehicleId = 1L;
//
//        this.mockMvc.perform(
//                        MockMvcRequestBuilders.put("/v1/vehicles/{userId}/{vehicleId}", userId, vehicleId)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content("""
//                            {
//                                "type": "VAN",
//                                "brand": "Fiat",
//                                "model": "Fiorino",
//                                "displacement": 1200,
//                                "color": "RED",
//                                "power": 70,
//                                "gear": "MANUAL",
//                                "registrationYear": 2022,
//                                "powerSupply": "METHANE",
//                                "originalPrice": 15000,
//                                "discountedPrice": 14000,
//                                "usedFlag": "NEW",
//                                "marketStatus": "AVAILABLE",
//                                "discountFlag": true,
//                                "engine": "4-cylinder"
//                            }
//                            """)
//                )
//                .andExpect(status().isOk());
//    }
//
//    /**
//     * Puoi modificare ogni parte di veicolo
//     *
//     */
//
//    @Test
//    void successfulStatusVehicleModification_test() throws Exception {
//        Roles roles = ADMIN;
//        long userId = 1L;
//        long vehicleId = 1L;
//
//        this.mockMvc.perform(MockMvcRequestBuilders.patch("/v1/vehicles/{userId}/{vehicleId}/status", userId, vehicleId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                            {
//                                "status": "NOTAVAILABLE"
//                            }
//                            """))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void successfulColorVehicleModification_test() throws Exception {
//        Roles roles = ADMIN;
//        long userId = 1L;
//        long vehicleId = 1L;
//
//        this.mockMvc.perform(MockMvcRequestBuilders.patch("/v1/vehicles/{userId}/{vehicleId}/status", userId, vehicleId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                            {
//                                "color": "YELLOW"
//                            }
//                            """))
//                .andExpect(status().isOk());
//    }
}
