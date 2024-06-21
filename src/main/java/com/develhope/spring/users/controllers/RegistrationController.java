package com.develhope.spring.users.controllers;

import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class RegistrationController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/profile/registration")
    public UserSavedDTO registerUserAccount(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.registerNewUserAccount(userRegistrationDTO);
    }
}
