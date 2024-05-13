package com.develhope.spring.model.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserCreation_emptyNameOrSurname() {
        assertThrows(EmptyParameterException.class, () -> new User(" ", "Salomon", 04356546734, "gigino@hey.com", Roles.ADMIN));
    }

    @Test
    void testUserCreation_wrongEmailFormat() {
        assertThrows(WrongEmailFormatException.class, () -> new User("Bello", "Salomon", 04356546734, "gigino.hey.com", Roles.ADMIN));
    }

    @Test
    void testUserCreation() throws EmptyParameterException, WrongEmailFormatException {
        User admin = new User("Bello", "Solomon", 01065567556, "gigino@hey.com", Roles.ADMIN);
        assertEquals("gigino@hey.com", admin.getEmail());
    }
}
