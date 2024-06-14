package com.develhope.spring.users.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private void insertAdmin() throws Exception {
        this.mockMvc.perform(post("/v1/profile/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "name": "Alessio",
                                   "surname":"Delle Donne",
                                   "username": "ilGrandeWorro",
                                   "password": "1234",
                                   "matchingPassword": "1234",
                                   "phoneNumber": 1234567890,
                                   "email":"mail@itsadmin.com",
                                   "roles":"ADMIN"
                                }
                                """)).andReturn();
    }


    private void insertBuyer() throws Exception {
        this.mockMvc.perform(post("/v1/profile/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "name": "Giovanni",
                                   "surname":"Giorgio",
                                   "username": "Giorgio",
                                   "password": "1234",
                                   "matchingPassword": "1234",
                                   "phoneNumber": 1234567890,
                                   "email":"mail@itsbuyer.com",
                                   "roles":"BUYER"
                                }
                                """)).andReturn();
    }


    void userUpdateTest() throws Exception {
        insertAdmin();
        insertBuyer();
        this.mockMvc.perform(patch("/v1/users/2")
                        .with(httpBasic("mail@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "name": "Giorgio",
                                "surname": "Mastrota",
                                "username": "Inox",
                                "phoneNumber": 123,
                                "email": "altra@email.it",
                                "role": "SALESPERSON"
                                }
                                """))
                .andDo(print())
        .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id": 2,
                        "name": "Giorgio",
                        "surname": "Mastrota",
                        "username": "Inox",
                        "phoneNumber": 123,
                        "email": "altra@email.it",
                        "role": "SALESPERSON"
                        }
                        """)).andReturn();
    }
    @Test
    void deleteUserTest() throws Exception {
        insertAdmin();
        insertBuyer();
        this.mockMvc.perform(delete("/v1/users/2")
                        .with(httpBasic("mail@itsadmin.com", "1234")))
                .andExpect(status().isOk())
                .andReturn();
    }
}
