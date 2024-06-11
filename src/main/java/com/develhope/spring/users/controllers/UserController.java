package com.develhope.spring.users.controllers;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserResponseDTO;
import com.develhope.spring.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO registerUser(@RequestBody UserCreatorDTO userCreatorDTO){
       return userService.createUser(userCreatorDTO);
    }

    @DeleteMapping("/{userID}/{userIDToDelete}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long userID, @PathVariable long userIDToDelete){
        userService.deleteUser(userID, userIDToDelete);
    }
}
