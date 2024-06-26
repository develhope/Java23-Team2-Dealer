package com.develhope.spring.vehicles.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration
@WebAppConfiguration
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
class VehicleIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private void insertAdmin() throws Exception {
        this.mockMvc.perform(post("/v1/profile/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "name": "Senior",
                           "surname":"Dello Iacovo",
                           "username": "panenutella",
                           "password": "1234",
                           "matchingPassword": "1234",
                           "phoneNumber": 3467796292,
                           "email":"hey@itsadmin.com",
                           "roles":"ADMIN"
                        }
                        """)).andReturn();
    }

    private void insertBuyer() throws Exception {
        this.mockMvc.perform(post("/profile/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "gigi",
                        "surname": "delpiero",
                        "phoneNumber": 3467796,
                        "email": "hey@itsme.it",
                        "roles": "BUYER"
                        }
                        """)).andReturn();
    }

    @Test
    void createVehicle_successfulCreationTest() throws Exception {
        insertAdmin();
        this.mockMvc.perform(post("/v1/vehicles")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "vehicleType": "VAN",
                                    "brand": "Fiat",
                                    "model": "Fiorino",
                                    "displacement": 1200,
                                    "color": "WHITE",
                                    "power": 70,
                                    "gear": "MANUAL",
                                    "registrationYear": 2022,
                                    "powerSupply": "METHANE",
                                    "price": 15000,
                                    "dailyCost": 40.00,
                                    "usedFlag": "NEW",
                                    "marketStatus": "AVAILABLE",
                                    "engine": "4-cylinder"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json(
                        """
                                {
                                  "id": 1,
                                  "vehicleType": "VAN",
                                  "brand": "Fiat",
                                  "model": "Fiorino",
                                  "color": "WHITE",
                                  "price": 15000,
                                  "usedFlag": "NEW",
                                  "marketStatus": "AVAILABLE"
                                }
                                """
                ))
                .andReturn();
    }

    @Test
    void vehicleCreation_NotAuthorizedAndExpectErrorTest() throws Exception {
        insertAdmin();
        insertBuyer();
        this.mockMvc.perform(post("/v1/vehicles")
                        .with(httpBasic("hey@itsadmin2.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "vehicleType": "VAN",
                                    "brand": "Fiat",
                                    "model": "Fiorino",
                                    "displacement": 1200,
                                    "color": "WHITE",
                                    "power": 70,
                                    "gear": "MANUAL",
                                    "registrationYear": 2022,
                                    "powerSupply": "METHANE",
                                    "price": 15000,
                                    "dailyCost": 40.00,
                                    "usedFlag": "NEW",
                                    "marketStatus": "AVAILABLE",
                                    "discountFlag": true,
                                    "engine": "4-cylinder"
                                }
                                """))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

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

    /**
     * Puoi modificare ogni parte di veicolo
     *
     */

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
