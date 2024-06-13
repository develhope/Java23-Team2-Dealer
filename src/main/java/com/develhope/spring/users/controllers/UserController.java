package com.develhope.spring.users.controllers;

import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long userID){
        userService.deleteUser(userID);
    }
}
