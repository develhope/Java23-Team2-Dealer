package com.develhope.spring.users.services;

import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    private long DEFAULT_ID = 1;

    private User DEFAULT_ADMIN = new User(1, "", "", 123, "", Roles.ADMIN);

    private User DEFAULT_BUYER = new User(2, "", "", 123, "", Roles.BUYER);

    private UserUpdaterDTO DEFAULT_UPDATER_DTO = new UserUpdaterDTO(
            "Sio",
            "Worro",
            123321,
            "ciao@bello.com"

    );

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void checkUserAuthorizationByTest() {
        when(userRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(DEFAULT_ADMIN));
        assertDoesNotThrow(() -> userService.checkUserAuthorizationBy(DEFAULT_ID));
    }

    @Test
    void checkUserAuthorizationByTest_NotAuthorized() {
        when(userRepository.findById(2L)).thenReturn(Optional.of(DEFAULT_BUYER));
        assertThrows(NotAuthorizedOperationException.class, () -> userService.checkUserAuthorizationBy(DEFAULT_ID));
    }

    @Test
    void updateUserTest() {
        when(userRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_ADMIN));
        User updatedUser = new User(
                DEFAULT_ADMIN.getId(),
                DEFAULT_UPDATER_DTO.getName(),
                DEFAULT_UPDATER_DTO.getSurname(),
                DEFAULT_UPDATER_DTO.getPhoneNumber(),
                DEFAULT_UPDATER_DTO.getEmail(),
                DEFAULT_ADMIN.getRoles()
        );
        when(userRepository.save(any()))
                .thenReturn(updatedUser);
        UserReworkedDTO expected = new UserReworkedDTO(
                1L,
                "Sio",
                "Worro",
                123321,
                "ciao@bello.com"
        );
        UserReworkedDTO result = userService.update(DEFAULT_ID, DEFAULT_UPDATER_DTO);
        assertEquals(expected.getName(), result.getName());
    }

    @Test
    void updateUserTest_checkIfIdIsUnchanged() {
        when(userRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_ADMIN));
        User updatedUser = new User(
                DEFAULT_ADMIN.getId(),
                DEFAULT_UPDATER_DTO.getName(),
                DEFAULT_UPDATER_DTO.getSurname(),
                DEFAULT_UPDATER_DTO.getPhoneNumber(),
                DEFAULT_UPDATER_DTO.getEmail(),
                DEFAULT_ADMIN.getRoles()
        );
        when(userRepository.save(any()))
                .thenReturn(updatedUser);
        UserReworkedDTO expected = new UserReworkedDTO(
                1L,
                "Sio",
                "Worro",
                123321,
                "ciao@bello.com"
        );
        UserReworkedDTO result = userService.update(DEFAULT_ID, DEFAULT_UPDATER_DTO);
        assertEquals(expected.getId(), result.getId());
    }
}

