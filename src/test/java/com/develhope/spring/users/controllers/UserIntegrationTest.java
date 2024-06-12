package com.develhope.spring.users.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private void insertUser() throws Exception {
        this.mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "Sio",
                        "surname": "Worro",
                        "phoneNumber": 123,
                        "email": "user@email.it",
                        "roles": "ADMIN"
                        }
                        """)).andReturn();
    }

    @Test
    void deleteUserTest() throws Exception {
        insertUser();
        this.mockMvc.perform(delete("/v1/users/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}