package com.develhope.spring.users.controllers;

import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserResponseDTO;
import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
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

    @PutMapping ("/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public UserReworkedDTO updateUser(@PathVariable long userID,
                                      @RequestBody UserUpdaterDTO userUpdaterDTO){
        return userService.update(userID, userUpdaterDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO registerUser(@RequestBody UserCreatorDTO userCreatorDTO){
       return userService.createUser(userCreatorDTO);
    }
}
