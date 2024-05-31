package com.develhope.spring.users.controllers;

import com.develhope.spring.users.components.Mapper;
import com.develhope.spring.users.dto.UserCreationDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreationDTO registerUser(@RequestBody UserCreationDTO userCreationDTO){
       return userService.createUser(userCreationDTO);
    }
}
