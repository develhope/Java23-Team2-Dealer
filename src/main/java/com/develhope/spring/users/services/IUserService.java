package com.develhope.spring.users.services;

import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserResponseDTO;

public interface IUserService {

    UserResponseDTO registerNewUserAccount(UserRegistrationDTO userRegistrationDTO);
}
