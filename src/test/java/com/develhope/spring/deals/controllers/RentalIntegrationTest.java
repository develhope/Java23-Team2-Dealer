package com.develhope.spring.deals.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RentalIntegrationTest {

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
                        "surname": "dello",
                        "phoneNumber": 3467796,
                        "email": "hey@itme.it",
                        "roles": "BUYER"
                        }
                        """)).andReturn();
    }

    private void insertVehicle() throws Exception {
        this.mockMvc.perform(post("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                        "vehicleType": "CAR",
                        "brand": "fiorino",
                        "model": "top del top",
                        "displacement": 4,
                        "color": "WHITE",
                        "power": 9999,
                        "gear": "MANUAL",
                        "registrationYear": 1700,
                        "powerSupply": "DIESEL",
                        "price": 54000.00,
                        "discountedPrice": null,
                        "usedFlag": "NEW",
                        "marketStatus": "AVAILABLE",
                        "discountedFlag": false,
                        "engine": "Al plasma"
                         }
                        """)).andReturn();
    }

    private void insertVehicleNotAvailable() throws Exception {
        this.mockMvc.perform(post("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                        "vehicleType": "CAR",
                        "brand": "fiorino",
                        "model": "top del top",
                        "displacement": 4,
                        "color": "WHITE",
                        "power": 9999,
                        "gear": "MANUAL",
                        "registrationYear": 1700,
                        "powerSupply": "DIESEL",
                        "price": 54000.00,
                        "discountedPrice": null,
                        "usedFlag": "NEW",
                        "marketStatus": "NOTAVAILABLE",
                        "discountedFlag": false,
                        "engine": "Al plasma"
                         }
                        """)).andReturn();
    }

    @Test
    void createRental_successfulTest() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "totalCost": 80.00,
                        "paid": true,
                        "vehicle": {
                                    "id": 1
                                    },
                        "buyer": {
                                 "id": 2
                                 }
                        }
                        """)).andReturn();
    }

    @Test
    void createRental_vehicleIsNotAvailableAndExceptionIsThrown() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicleNotAvailable();
        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andExpect(status().isConflict());
    }

    @Test
    void createRental_overlappingDatesAndExceptionIsThrown() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andReturn();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-06",
                        "endDate": "2024-06-09",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andReturn();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-09",
                        "endDate": "2024-06-10",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    @Test
    void updateRental_SuccessfulUpdatingTest() throws Exception {
        insertAdmin();
        insertBuyer();
        insertBuyer();
        insertVehicle();
        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                {
                "startDate": "2024-06-03",
                "endDate": "2024-06-05",
                "dailyCost": 40.00,
                "paid": true,
                "vehicleId": 1,
                "userId": 2
                }
                """).contentType(MediaType.APPLICATION_JSON)).andReturn();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                {
                "startDate": "2024-06-06",
                "endDate": "2024-06-08",
                "dailyCost": 40.00,
                "paid": true,
                "vehicleId": 1,
                "userId": 3
                }
                """).contentType(MediaType.APPLICATION_JSON)).andReturn();

        this.mockMvc.perform(patch("/v1/rentals/1/1").contentType(MediaType.APPLICATION_JSON).content(
                        """
                                {
                                    "startDate": "2024-06-09",
                                    "endDate": "2024-06-12",
                                    "dailyCost": 40.00,
                                    "paid": false,
                                    "vehicleId": 1
                                }
                                """
                ))
                .andExpect(status().isAccepted())
                .andExpect(content().json("""
                        {
                        "startDate": "2024-06-09",
                        "endDate": "2024-06-12",
                        "dailyCost": 40.00,
                        "totalCost": 120.00,
                        "paid": false,
                        "vehicle": {
                                    "id": 1
                                    },
                        "buyer": {
                                 "id": 2
                                 }
                        }
                        """)).andReturn();
    }

    @Test
    void loadByUserId_successfulRetrievingRentals() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andReturn();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-06",
                        "endDate": "2024-06-09",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andReturn();

        this.mockMvc.perform(get("/v1/rentals/2?page=0&size=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[1].endDate", is("2024-06-05")))
                .andExpect(jsonPath("$.content[0].startDate", is("2024-06-06")))
                .andReturn();
    }

    @Test
    void loadByUserId_userNotRegistered() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andReturn();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-06",
                        "endDate": "2024-06-09",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andReturn();

        this.mockMvc.perform(get("/v1/rentals/3?page=0&size=5"))
                .andExpect(status().isNotFound()).andReturn();
    }
}