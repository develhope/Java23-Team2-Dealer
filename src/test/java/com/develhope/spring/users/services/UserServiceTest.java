package com.develhope.spring.users.services;

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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    public static final long DEFAULT_USER_ID = 1L;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    
    private User setupWithRole(Roles role){
        User user = new User(DEFAULT_USER_ID);
        user.setRoles(role);
        return user;
    }

    @Test
    void checkUserAuthorizationTestThrowsIfUserEmpty(){
        when(userRepository.findById(DEFAULT_USER_ID))
                .thenReturn(Optional.empty());
        assertThrows(NotAuthorizedOperationException.class,()->
                userService.checkUserAuthorizationBy(DEFAULT_USER_ID));
    }

    @Test
    void checkUserAuthorizationTest_UserNotAuthorizedThrows(){
        User user = setupWithRole(Roles.BUYER);
        when(userRepository.findById(DEFAULT_USER_ID))
                .thenReturn(Optional.of(user));
        assertThrows(NotAuthorizedOperationException.class, () ->
                userService.checkUserAuthorizationBy(DEFAULT_USER_ID));
    }

    @Test
    void checkUserAuthorizationTest_UserAuthorizedNotThrows(){
        User user = setupWithRole(Roles.ADMIN);
        when(userRepository.findById(DEFAULT_USER_ID))
                .thenReturn(Optional.of(user));
        assertDoesNotThrow(() -> userService.checkUserAuthorizationBy(DEFAULT_USER_ID));
    }
}
