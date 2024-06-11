package com.develhope.spring.vehicles.controllers;

import com.develhope.spring.users.models.Roles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.develhope.spring.users.models.Roles.ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class VehicleIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private void insertAdmin() throws Exception {
        this.mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "gabriel",
                        "surname": "dello",
                        "phoneNumber": 3467796,
                        "email": "hey@itsadmin.it",
                        "roles": "ADMIN"
                        }
                        """)).andReturn();
    }

    private void insertBuyer() throws Exception {
        this.mockMvc.perform(post("/v1/users")
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
        this.mockMvc.perform(post("/v1/vehicles/1")
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
        this.mockMvc.perform(post("/v1/vehicles/2")
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
                                            "originalPrice": 15000,
                                            "usedFlag": "NEW",
                                            "marketStatus": "AVAILABLE",
                                            "discountFlag": true,
                                            "engine": "4-cylinder"
                                        }
                                        """))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    void vehicleCreation_adminNotFoundAndExpectErrorTest() throws Exception {
        insertAdmin();
        insertBuyer();
        this.mockMvc.perform(post("/v1/vehicles/4")
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
                                            "originalPrice": 15000,
                                            "usedFlag": "NEW",
                                            "marketStatus": "AVAILABLE",
                                            "discountFlag": true,
                                            "engine": "4-cylinder"
                                        }
                                        """))
                .andExpect(status().isNotFound())
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
