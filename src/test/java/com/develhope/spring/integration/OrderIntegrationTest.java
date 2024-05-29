package com.develhope.spring.integration;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createOrder() throws Exception {
        this.mockMvc.perform(
                post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "downPayment" : true,
                                    "vehicleId" : 1,
                                    "userId" : 1,
                                    "adminID" : 1
                                }
                                """)
        )
                .andExpect(status().isOk());

    }
}
