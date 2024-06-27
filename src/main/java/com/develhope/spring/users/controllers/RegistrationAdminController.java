package com.develhope.spring.users.controllers;

import com.develhope.spring.users.dtos.UserAdminRegistrationDTO;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@Profile({"dev", "test"})
@RestController
@RequestMapping("/v1")
public class RegistrationAdminController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(RegistrationAdminController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/profile/registration")
    public UserSavedDTO registerUserAccount(@RequestBody UserAdminRegistrationDTO userAdminRegistrationDTO) {
        logger.info("Registration request received " + userAdminRegistrationDTO);
        return userService.registerNewAdminAccount(userAdminRegistrationDTO);
    }
}
