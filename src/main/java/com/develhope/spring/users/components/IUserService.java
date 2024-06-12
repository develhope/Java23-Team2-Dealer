package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;

public interface IUserService {

    UserSavedDTO registerNewUserAccount(UserRegistrationDTO userRegistrationDTO);
}
