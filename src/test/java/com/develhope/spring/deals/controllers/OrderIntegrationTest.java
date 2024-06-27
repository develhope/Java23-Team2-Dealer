package com.develhope.spring.deals.controllers;

import com.develhope.spring.exceptions.NotAuthorizedOperationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderIntegrationTest {

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
    private void insertSeller() throws Exception {
        this.mockMvc.perform(post("/v1/profile/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Giorgio",
                            "surname":"Mastrota",
                            "username": "eminflex",
                            "password": "1234",
                            "matchingPassword": "1234",
                            "phoneNumber": 3467796292,
                            "email":"hey@itsseller.com",
                            "roles":"SALESPERSON"
                         }
                        """)).andReturn();
    }

    private void insertBuyer() throws Exception {
        this.mockMvc.perform(post("/v1/profile/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Pietro",
                            "surname":"Pacciani",
                            "username": "MostroDiFirenze",
                            "password": "12345",
                            "matchingPassword": "12345",
                            "phoneNumber": 34427796292,
                            "email":"hey@itsbuyer.com",
                            "roles":"BUYER"
                         }
                        """)).andReturn();
    }

    private void insertVehicle() throws Exception {
        this.mockMvc.perform(post("/v1/vehicles")
                .with(httpBasic("hey@itsadmin.com", "1234"))
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

    private void insertOrder() throws Exception {
        this.mockMvc.perform(post("/v1/orders")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": 1,
                                "userId": 2,
                                "orderStatus": "PAID",
                                "paid": true
                                }
                                """))
                .andReturn();
    }

    private void insertOrder_nullSeller() throws Exception {
        this.mockMvc.perform(post("/v1/orders")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": null,
                                "userId": 2,
                                "orderStatus": "PAID",
                                "paid": true
                                }
                                """))
                .andReturn();
    }
    @Test
    void createOrder() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();

        this.mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": 1,
                                "userId": 2,
                                "orderStatus": "PAID",
                                "paid": true
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                        "id": 1,
                        "downPayment": true,
                        "vehicle": {
                                    "id": 1
                                    },
                        "sellerId": 1,
                        "userId": 2,
                        "orderStatus": "PAID",
                        "paid": true
                        }
                        """)).andReturn();
    }

    @Test
    void adminOrderUpdateTest() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();
        insertOrder();

        this.mockMvc.perform((patch("/v1/orders/1")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": 1,
                                "userId": 2,
                                "orderStatus": "PENDING",
                                "paid": false
                                }
                                """)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                        {
                        "id": 1,
                        "sellerId": 1,
                        "downPayment": true,
                        "orderStatus": "PENDING",
                        "paid": false
                        }
                        """
                )).andReturn();
    }

    @Test
    void sellerOrderUpdateTest() throws Exception {
        insertSeller();
        insertAdmin();
        insertVehicle();
        insertOrder();

        this.mockMvc.perform((patch("/v1/orders/1")
                        .with(httpBasic("hey@itsseller.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": 1,
                                "userId": 2,
                                "orderStatus": "PENDING",
                                "paid": false
                                }
                                """)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                "id": 1,
                                "sellerId": 1,
                                "downPayment": true,
                                "orderStatus": "PENDING",
                                "paid": false
                                }
                                """
                )).andReturn();
    }

    @Test
    void buyerOrderUpdateTest_forbidden() throws Exception {
        insertBuyer();
        insertAdmin();
        insertVehicle();
        insertOrder();

        this.mockMvc.perform((patch("/v1/orders/1")
                        .with(httpBasic("hey@itsbuyer.com", "12345"))
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
                .andExpect(status().isForbidden()).andReturn();
    }

    @Test
    void createOrderAndDeletedByADMIN_successfulTest() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();
        insertOrder();

        this.mockMvc.perform(post("/v1/orders")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": 1,
                                "userId": 2,
                                "orderStatus": "PAID",
                                "paid": true
                                }
                                """))
                .andExpect(status().isCreated());

        this.mockMvc.perform(delete("/v1/orders/1")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }


    @Test
    void buyerCreateAndDeleteOrder_successfulTest() throws Exception {
        insertAdmin();
        insertVehicle();
        insertBuyer();

        this.mockMvc.perform(post("/v1/orders")
                        .with(httpBasic("hey@itsbuyer.com", "12345"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": 1,
                                "userId": 2,
                                "orderStatus": "PAID",
                                "paid": true
                                }
                                """))
                .andExpect(status().isCreated()).andReturn();


        this.mockMvc.perform(delete("/v1/orders/1")
                        .with(httpBasic("hey@itsbuyer.com", "12345"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void unauthorizedBuyerCannotDeleteOrder() throws Exception {
        insertAdmin();
        insertVehicle();
        insertBuyer();

        this.mockMvc.perform(post("/v1/profile/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Luffy",
                            "surname":"Monkey D.",
                            "username": "Mugiwara",
                            "password": "54321",
                            "matchingPassword": "54321",
                            "phoneNumber": 1234567890,
                            "email":"hey@itsbuyer2.com",
                            "roles":"BUYER"
                         }
                        """)).andReturn();

        this.mockMvc.perform(post("/v1/orders")
                        .with(httpBasic("hey@itsbuyer.com", "12345"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": 1,
                                "userId": 2,
                                "orderStatus": "PAID",
                                "paid": true
                                }
                                """))
                .andExpect(status().isCreated()).andReturn();


        this.mockMvc.perform(delete("/v1/orders/1")
                        .with(httpBasic("hey@itsbuyer2.com", "54321"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(result -> assertInstanceOf(NotAuthorizedOperationException.class, result.getResolvedException()))
                .andReturn();
    }

    @Test
    void createOrder_nullSeller() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();

        this.mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": null,
                                "userId": 2,
                                "orderStatus": "PAID",
                                "paid": true
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                        "id": 1,
                        "downPayment": true,
                        "vehicle": {
                                    "id": 1
                                    },
                        "sellerId": null,
                        "userId": 2,
                        "orderStatus": "PAID",
                        "paid": true
                        }
                        """)).andReturn();
    }

    @Test
    void orderUpdateTest_nullSeller() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();
        insertOrder_nullSeller();

        this.mockMvc.perform((patch("/v1/orders/1")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": null,
                                "userId": 2,
                                "orderStatus": "PENDING",
                                "paid": false
                                }
                                """)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                        {
                        "id": 1,
                        "sellerId": null,
                        "downPayment": true,
                        "orderStatus": "PENDING",
                        "paid": false
                        }
                        """
                )).andReturn();
    }

    @Test
    void orderUpdateTest_nullSeller_addSeller() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();
        insertOrder_nullSeller();

        this.mockMvc.perform((patch("/v1/orders/1")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": 1,
                                "userId": 2,
                                "orderStatus": "PENDING",
                                "paid": false
                                }
                                """)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                        {
                        "id": 1,
                        "sellerId": 1,
                        "downPayment": true,
                        "orderStatus": "PENDING",
                        "paid": false
                        }
                        """
                )).andReturn();
    }
    @Test
    void orderUpdateTest_nullSeller_removeSeller() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();
        insertOrder();

        this.mockMvc.perform((patch("/v1/orders/1")
                        .with(httpBasic("hey@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "downPayment": true,
                                "vehicleId": 1,
                                "sellerId": null,
                                "userId": 2,
                                "orderStatus": "PENDING",
                                "paid": false
                                }
                                """)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                        {
                        "id": 1,
                        "sellerId": null,
                        "downPayment": true,
                        "orderStatus": "PENDING",
                        "paid": false
                        }
                        """
                )).andReturn();
    }


    @Test
    void buyerOrderUpdateTest_forbidden_nullSeller() throws Exception {
        insertBuyer();
        insertAdmin();
        insertVehicle();
        insertOrder();

        this.mockMvc.perform((patch("/v1/orders/1")
                        .with(httpBasic("hey@itsbuyer.com", "12345"))
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
                .andExpect(status().isForbidden()).andReturn();
    }
}
