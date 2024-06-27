package com.develhope.spring.users.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private void insertSeller() throws Exception {
        this.mockMvc.perform(post("/v1/profile/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "name": "Oscar",
                           "surname":"Afaggio",
                           "username": "Lady",
                           "password": "1234",
                           "matchingPassword": "1234",
                           "phoneNumber": 1234567890,
                           "email":"mail@itsseller.com",
                           "roles": "SALESPERSON"
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

    private void insertBuyer2() throws Exception {
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

    @Test
    void adminUpdateUser_roleIsUnchangedTest() throws Exception {
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
                                "email": "altra@email.it"
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
                        "role": "BUYER"
                        }
                        """)).andReturn();
    }

    @Test
    void adminUpdateUserRole_andRoleIsChangedTest() throws Exception {
        insertAdmin();
        insertBuyer();
        this.mockMvc.perform(patch("/v1/users/role/2")
                        .with(httpBasic("mail@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "role": "SALESPERSON"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id": 2,
                        "name": "Giovanni",
                        "surname": "Giorgio",
                        "username": "Giorgio",
                        "phoneNumber": 1234567890,
                        "email": "mail@itsbuyer.com",
                        "role": "SALESPERSON"
                        }
                        """)).andReturn();
    }

    @Test
    void adminUpdateUserRole_newSellerTryToUpdateHimSelf_andExpectForbiddenTest() throws Exception {
        insertAdmin();
        insertBuyer();
        this.mockMvc.perform(patch("/v1/users/role/2")
                        .with(httpBasic("mail@itsadmin.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "role": "SALESPERSON"
                                }
                                """))
                .andReturn();
        this.mockMvc.perform(patch("/v1/users/2")
                        .with(httpBasic("mail@itsbuyer.com", "1234"))
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
                .andExpect(status().isForbidden()).andReturn();

    }


    @Test
    void userSelfUpdateTest() throws Exception {
        insertBuyer();
        this.mockMvc.perform(patch("/v1/users/1")
                        .with(httpBasic("mail@itsbuyer.com", "1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "name": "Giorgio",
                                "surname": "Mastrota",
                                "username": "Inox",
                                "phoneNumber": 123,
                                "email": "altra@email.it"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id": 1,
                        "name": "Giorgio",
                        "surname": "Mastrota",
                        "username": "Inox",
                        "phoneNumber": 123,
                        "email": "altra@email.it",
                        "role": "BUYER"
                        }
                        """)).andReturn();
    }

    @Test
    void userUpdateOtherTestForbidden() throws Exception {
        insertBuyer();
        insertBuyer2();
        this.mockMvc.perform(patch("/v1/users/2")
                        .with(httpBasic("mail@itsbuyer.com", "1234"))
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
                .andExpect(status().isForbidden()).andReturn();
    }

    @Test
    void sellerUpdateOtherTestForbidden() throws Exception {
        insertBuyer();
        insertSeller();
        this.mockMvc.perform(patch("/v1/users/1")
                        .with(httpBasic("mail@itsseller.com", "1234"))
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
                .andExpect(status().isForbidden()).andReturn();
    }

    @Test
    void sellerUpdateSelfTestForbidden() throws Exception {
        insertSeller();
        this.mockMvc.perform(patch("/v1/users/1")
                        .with(httpBasic("mail@itsseller.com", "1234"))
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
                .andExpect(status().isForbidden()).andReturn();
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
