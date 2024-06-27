package com.develhope.spring.users.services;

import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserRoleUpdaterDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    private final long DEFAULT_ID = 1;

    private final User DEFAULT_BUYER = new User(1, "Alessio", "Delle Donne", "ilGrandeWorro", "password1", 123, "indirizzo@email.com", Roles.BUYER);

    private final UserUpdaterDTO DEFAULT_UPDATER_DTO = new UserUpdaterDTO(
            "Sio",
            "Asburgo",
            "coriandolo",
            123321,
            "ciao@bello.com"
    );

    private UserRoleUpdaterDTO DEFAULT_UPDATER_ROLE_DTO = new UserRoleUpdaterDTO(
            Roles.SALESPERSON
    );

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User setupWithRole(Roles role) {
        User user = new User(DEFAULT_ID);
        user.setRole(role);
        return user;
    }

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
                DEFAULT_BUYER.getRole()
        );
        when(userRepository.save(any()))
                .thenReturn(updatedUser);
        UserReworkedDTO expected = new UserReworkedDTO(
                1L,
                "Sio",
                "Asburgo",
                "coriandolo",
                123321,
                "ciao@bello.com",
                Roles.BUYER
        );
        UserReworkedDTO result = userService.update(DEFAULT_ID, DEFAULT_UPDATER_DTO);
        assertEquals(expected.getName(), result.getName());
    }

    @Test
    void updateUserTest_roleUnchanged() {
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
                DEFAULT_BUYER.getRole()
        );
        when(userRepository.save(any()))
                .thenReturn(updatedUser);
        UserReworkedDTO expected = new UserReworkedDTO(
                1L,
                "Sio",
                "Asburgo",
                "coriandolo",
                123321,
                "ciao@bello.com",
                Roles.BUYER
        );
        UserReworkedDTO result = userService.update(DEFAULT_ID, DEFAULT_UPDATER_DTO);
        assertEquals(expected.getRole(), result.getRole());
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
                DEFAULT_BUYER.getRole()
        );
        when(userRepository.save(any()))
                .thenReturn(updatedUser);
        UserReworkedDTO expected = new UserReworkedDTO(
                1L,
                "Sio",
                "Asburgo",
                "coriandolo",
                123321,
                "ciao@bello.com",
                Roles.BUYER
        );
        UserReworkedDTO result = userService.update(DEFAULT_ID, DEFAULT_UPDATER_DTO);
        assertEquals(expected.getId(), result.getId());
    }

    @Test
    void updateUserRoleTest_checkIfRoleIsUpdated() {
        when(userRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_BUYER));
        User updatedUser = DEFAULT_BUYER;
        updatedUser.setRole(Roles.SALESPERSON);
        when(userRepository.save(any()))
                .thenReturn(updatedUser);
        UserReworkedDTO expected = new UserReworkedDTO(
                1L,
                DEFAULT_BUYER.getName(),
                DEFAULT_BUYER.getSurname(),
                DEFAULT_BUYER.getUsername(),
                DEFAULT_BUYER.getPhoneNumber(),
                DEFAULT_BUYER.getEmail(),
                Roles.SALESPERSON
        );
        UserReworkedDTO result = userService.updateRole(DEFAULT_ID, DEFAULT_UPDATER_ROLE_DTO);
        assertEquals(expected.getRole(), result.getRole());
    }

    @Test
    void updateUserRoleTest_checkIfRoleIsUpdated_butUserNameStillTheSame() {
        when(userRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_BUYER));
        User updatedUser = DEFAULT_BUYER;
        updatedUser.setRole(Roles.SALESPERSON);
        when(userRepository.save(any()))
                .thenReturn(updatedUser);
        UserReworkedDTO expected = new UserReworkedDTO(
                1L,
                DEFAULT_BUYER.getName(),
                DEFAULT_BUYER.getSurname(),
                DEFAULT_BUYER.getUsername(),
                DEFAULT_BUYER.getPhoneNumber(),
                DEFAULT_BUYER.getEmail(),
                Roles.SALESPERSON
        );
        UserReworkedDTO result = userService.updateRole(DEFAULT_ID, DEFAULT_UPDATER_ROLE_DTO);
        assertEquals(expected.getUsername(), result.getUsername());
    }
}

