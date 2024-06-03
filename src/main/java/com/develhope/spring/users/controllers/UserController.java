package com.develhope.spring.users.controllers;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.users.services.UserService;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> getException(UserNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(NotAuthorizedOperationException.class)
    public ResponseEntity<String> getException(NotAuthorizedOperationException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserSavedDTO registerUser(@RequestBody UserCreatorDTO userCreatorDTO){
       return userService.createUser(userCreatorDTO);
    }

    @DeleteMapping("/{userID}/{userIDToDelete}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long userID, @PathVariable long userIDToDelete){
        userService.deleteUser(userID, userIDToDelete);
    }
}
