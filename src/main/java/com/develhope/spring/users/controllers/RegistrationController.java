package com.develhope.spring.users.controllers;

import com.develhope.spring.users.components.UserAlreadyExistException;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

//    @GetMapping("/user/registration")
//    public String showRegistrationForm(WebRequest request, Model model) {
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
//        model.addAttribute("user", userRegistrationDTO);
//        return "registration";
//    }

    @PostMapping("/user/registration")
    public UserSavedDTO registerUserAccount(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.registerNewUserAccount(userRegistrationDTO);
    }
}
