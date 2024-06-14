package com.develhope.spring.users.controllers;

import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
import com.develhope.spring.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PatchMapping ("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ADMIN")
    public UserReworkedDTO updateUser(@PathVariable long userID,
                                      @RequestBody UserUpdaterDTO userUpdaterDTO){
        return userService.update(userID, userUpdaterDTO);
    }
}
