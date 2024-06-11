package com.develhope.spring.deals.controllers;


import com.develhope.spring.deals.responseStatus.OrderNotFoundException;
import com.develhope.spring.deals.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @BeforeEach
    void setUp() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();
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

    @Test
    void createOrder_successfulTest() throws Exception {
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
                                """))
                .andReturn();
        doNothing().when(orderService).delete(1L);

        this.mockMvc.perform(delete("/v1/orders/{orderId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "message": "Order deleted successfully"
                        }
                        """));

        verify(orderService, times(1)).delete(1L);
    }

    @Test
    void deleteOrder_validInput_returnsOk() throws Exception {
        Long orderId = 1L;

        mockMvc.perform(delete("/v1/orders/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Order deleted successfully"));
    }

    @Test
    void deleteOrder_orderNotFoundExceptionTest() throws Exception {
        MvcResult createResult = this.mockMvc.perform(post("/v1/orders")
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
                .andReturn();

        doThrow(new OrderNotFoundException("Order with ID 1 not found", new NoSuchElementException()))
                .when(orderService).delete(1L);

        this.mockMvc.perform(delete("/v1/orders/{orderId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Order with ID 1 not found"));
    }
}