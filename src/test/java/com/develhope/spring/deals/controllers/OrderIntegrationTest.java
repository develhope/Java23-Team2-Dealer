package com.develhope.spring.deals.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

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

    private void insertOrder() throws Exception {
        this.mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                        "downPayment": true,
                        "vehicleId": 1,
                        "userId": 1,
                        "orderStatus": "PAID",
                        "paid": true
                        }
                        """))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void OrderUpdateTest() throws Exception {
        insertAdmin();
        insertVehicle();
        insertOrder();

        this.mockMvc.perform((patch("/v1/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "downPayment": true,
                        "vehicleId": 1,
                        "userId": 1,
                        "orderStatus": "PENDING",
                        "paid": false
                        }
                        """)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                        {
                        "downPayment": true,
                        "orderStatus": "PENDING",
                        "paid": false
                        }
                        """
                )).andReturn();
    }
}
