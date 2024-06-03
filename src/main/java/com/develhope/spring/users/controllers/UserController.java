package com.develhope.spring.users.controllers;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dto.UserCreatorDTO;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreatorDTO registerUser(@RequestBody UserCreatorDTO userCreatorDTO){
       return userService.createUser(userCreatorDTO);
    }
}
