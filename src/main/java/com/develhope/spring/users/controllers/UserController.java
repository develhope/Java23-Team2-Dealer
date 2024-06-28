package com.develhope.spring.users.controllers;

import com.develhope.spring.users.dtos.*;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.develhope.spring.exceptions.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(RegistrationAdminController.class);

    @PatchMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ADMIN", "BUYER"})
    public UserReworkedDTO updateUser(@PathVariable long userID, @AuthenticationPrincipal User userDetails,
                                      @RequestBody UserUpdaterDTO userUpdaterDTO) {
        boolean isAdmin = userDetails.getRole().equals(Roles.ADMIN);
        if (isAdmin) {
            return userService.update(userID, userUpdaterDTO);
        } else {
            if (userService.checkIfItsUserOwnID(userID, userDetails)) {
                return userService.update(userID, userUpdaterDTO);
            } else {
                throw new NotAuthorizedOperationException("You are not authorized to change someone else's data.");
            }
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @Secured("ADMIN")
    @PutMapping("/{userId}")
    public UserReworkedDTO updateUserRole(@PathVariable long userId, @RequestBody UserRoleUpdaterDTO userRoleUpdaterDTO) {
        return userService.updateRole(userId, userRoleUpdaterDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    public UserSavedDTO registerUserAccount(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        logger.info("Registration request received " + userRegistrationDTO);
        return userService.registerNewUserAccount(userRegistrationDTO);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long userID) {
        userService.deleteUser(userID);
    }
}
