package com.develhope.spring.users.controllers;

import com.develhope.spring.users.dtos.UserUpdaterDTO;
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
public class UserIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private void insertAdmin() throws Exception {
        this.mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "Sio",
                        "surname": "Worro",
                        "phoneNumber": 123,
                        "email": "ciao@bello.it",
                        "roles": "ADMIN"
                        }
                        """)).andReturn();
    }
    @Test
    void userUpdateTest() throws Exception {
        insertAdmin();
        this.mockMvc.perform(patch("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "Alessio",
                         "surname": "Delle Donne",
                         "phoneNumber": 123,
                         "email": "indirizzo@email.it"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id": 1,
                        "name": "Alessio",
                         "surname": "Delle Donne",
                         "phoneNumber": 123,
                         "email": "indirizzo@email.it"
                        }
                        """)).andReturn();
    }
}
