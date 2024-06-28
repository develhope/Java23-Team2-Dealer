package com.develhope.spring.users.services;

import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;

public interface IUserService {

    UserSavedDTO registerNewUserAccount(UserRegistrationDTO userRegistrationDTO);

    UserSavedDTO registerNewAdminAccount(UserRegistrationDTO userRegistrationDTO);
}
