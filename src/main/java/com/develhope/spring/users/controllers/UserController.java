package com.develhope.spring.users.controllers;

import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserRoleUpdaterDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.services.UserService;
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


    @PatchMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ADMIN", "BUYER"})
    public UserReworkedDTO updateUser(@PathVariable long userID, @AuthenticationPrincipal User userDetails,
                                      @RequestBody UserUpdaterDTO userUpdaterDTO) {
        boolean isAdmin = userDetails.getRole().equals(Roles.ADMIN);
        boolean isBuyer = userDetails.getRole().equals(Roles.BUYER);
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
    @PatchMapping("/role/{userId}")
    public UserReworkedDTO updateUserRole(@PathVariable long userId, @RequestBody UserRoleUpdaterDTO userRoleUpdaterDTO) {
        return userService.updateRole(userId, userRoleUpdaterDTO);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long userID) {
        userService.deleteUser(userID);
    }
}
