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

    private User DEFAULT_BUYER = new User(1,"Alessio", "Delle Donne", "ilGrandeWorro", "password1", 123, "indirizzo@email.com", Roles.BUYER);

    private User DEFAULT_ADMIN = new User(2, "Don", "Matteo", "DetectiveConan","password2", 123, "altra@email.com", Roles.ADMIN);



    private UserUpdaterDTO DEFAULT_UPDATER_DTO = new UserUpdaterDTO(
            "Sio",
            "Asburgo",
            "coriandolo",
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
    void updateUserTest() {
        when(userRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_BUYER));
        User updatedUser = new User(
                DEFAULT_BUYER.getId(),
                DEFAULT_UPDATER_DTO.getName(),
                DEFAULT_UPDATER_DTO.getSurname(),
                DEFAULT_UPDATER_DTO.getUsername(),
                DEFAULT_BUYER.getPassword(),
                DEFAULT_UPDATER_DTO.getPhoneNumber(),
                DEFAULT_UPDATER_DTO.getEmail(),
                DEFAULT_BUYER.getRoles()
        );
        when(userRepository.save(any()))
                .thenReturn(updatedUser);
        UserReworkedDTO expected = new UserReworkedDTO(
                1L,
                "Sio",
                "Asburgo",
                "coriandolo",
                123321,
                "ciao@bello.com"
        );
        UserReworkedDTO result = userService.update(DEFAULT_ID, DEFAULT_UPDATER_DTO);
        assertEquals(expected.getName(), result.getName());
    }

    @Test
    void updateUserTest_checkIfIdIsUnchanged() {
        when(userRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_BUYER));
        User updatedUser = new User(
                DEFAULT_BUYER.getId(),
                DEFAULT_UPDATER_DTO.getName(),
                DEFAULT_UPDATER_DTO.getSurname(),
                DEFAULT_UPDATER_DTO.getUsername(),
                DEFAULT_BUYER.getPassword(),
                DEFAULT_UPDATER_DTO.getPhoneNumber(),
                DEFAULT_UPDATER_DTO.getEmail(),
                DEFAULT_BUYER.getRoles()
        );
        when(userRepository.save(any()))
                .thenReturn(updatedUser);
        UserReworkedDTO expected = new UserReworkedDTO(
                1L,
                "Sio",
                "Asburgo",
                "coriandolo",
                123321,
                "ciao@bello.com"
        );
        UserReworkedDTO result = userService.update(DEFAULT_ID, DEFAULT_UPDATER_DTO);
        assertEquals(expected.getId(), result.getId());
    }
}

