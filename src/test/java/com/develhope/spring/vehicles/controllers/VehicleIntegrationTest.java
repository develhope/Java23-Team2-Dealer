package com.develhope.spring.vehicles.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@WebAppConfiguration
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@ActiveProfiles(profiles = "test")
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
        this.mockMvc.perform(post("/v1/users/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Pietro",
                            "surname":"Pacciani",
                            "username": "MostroDiFirenze",
                            "password": "12345",
                            "matchingPassword": "12345",
                            "phoneNumber": 34427796292,
                            "email":"hey@itsbuyer.com"
                         }
                        """)).andReturn();
    }

    private void insertVehicle() throws Exception {
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
                                  "dailyCost": 40.00,
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

    @Test
    void vehicleUpdateTest() throws Exception {
        insertVehicle();
        this.mockMvc.perform(put("/v1/vehicles/1")
                .with(httpBasic("hey@itsadmin.com", "1234"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                    "vehicleType": "CAR",
                                    "brand": "Ferrari",
                                    "model": "boh",
                                    "displacement": 51,
                                    "color": "RED",
                                    "power": 70000,
                                    "gear": "AUTOMATIC",
                                    "registrationYear": 2024,
                                    "powerSupply": "GPL",
                                    "price": 9000,
                                    "dailyCost": 40.00,
                                    "usedFlag": "NEW",
                                    "marketStatus": "AVAILABLE",
                                    "engine": "uno a caso"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                {
                                    "id": 1,
                                    "vehicleType": "CAR",
                                    "brand": "Ferrari",
                                    "model": "boh",
                                    "displacement": 51,
                                    "color": "RED",
                                    "power": 70000,
                                    "gear": "AUTOMATIC",
                                    "registrationYear": 2024,
                                    "powerSupply": "GPL",
                                    "price": 9000,
                                    "dailyCost": 40.00,
                                    "usedFlag": "NEW",
                                    "marketStatus": "AVAILABLE",
                                    "engine": "uno a caso"
                                }
                                """)).andReturn();
    }

    @Test
    void vehicleUpdateStatusTest() throws Exception {
        insertVehicle();
        this.mockMvc.perform(patch("/v1/vehicles/1/status")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "marketStatus": "ORDERABLE"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                {
                                    "id": 1,
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
                                    "marketStatus": "ORDERABLE",
                                    "engine": "4-cylinder"
                                }
                                """)).andReturn();
    }
    @Test
    void vehicleUpdateTestForbidden() throws Exception {
        insertVehicle();
        insertBuyer();
        this.mockMvc.perform(put("/v1/vehicles/1")
                        .with(httpBasic("hey@itsbuyer.com", "12345"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "vehicleType": "CAR",
                                    "brand": "Ferrari",
                                    "model": "boh",
                                    "displacement": 51,
                                    "color": "RED",
                                    "power": 70000,
                                    "gear": "AUTOMATIC",
                                    "registrationYear": 2024,
                                    "powerSupply": "GPL",
                                    "price": 9000,
                                    "dailyCost": 40.00,
                                    "usedFlag": "NEW",
                                    "marketStatus": "AVAILABLE",
                                    "engine": "uno a caso"
                                }
                                """))
                .andExpect(status().isForbidden()).andReturn();
    }

    @Test
    void vehicleUpdateStatusTestForbidden() throws Exception {
        insertVehicle();
        insertBuyer();
        this.mockMvc.perform(patch("/v1/vehicles/1/status")
                        .with(httpBasic("hey@itsbuyer.com", "12345"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "marketStatus": "ORDERABLE"
                                }
                                """))
                .andExpect(status().isForbidden()).andReturn();
    }
}
