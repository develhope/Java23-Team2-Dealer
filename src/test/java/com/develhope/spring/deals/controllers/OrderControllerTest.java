package com.develhope.spring.deals.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderController orderController;

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
                        "originalPrice": 54000.00,
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
                        "originalPrice": 54000.00,
                        "discountedPrice": null,
                        "usedFlag": "NEW",
                        "marketStatus": "NOTAVAILABLE",
                        "discountedFlag": false,
                        "engine": "Al plasma"
                         }
                        """)).andReturn();
    }

    @Test
    void createOrder_successfulTest() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();
        this.mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment" : true,
                                "vehicleId" : 1,
                                "userId" : 1,
                                "orderStatus" : "PAID",
                                "isPaid" : true
                                }
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteOrder_successfulTest() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();
        MvcResult result = this.mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment" : true,
                                "vehicleId" : 1,
                                "userId" : 1,
                                "orderStatus" : "PAID",
                                "isPaid" : true
                                }
                                """)
                ).andExpect(status().isCreated())
                .andReturn();

        this.mockMvc.perform(delete("/v1/orders/{orderId}", 1) // Assuming orderId is 1 in this case
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "message": "Order deleted successfully"
                        }
                        """));
    }


}


