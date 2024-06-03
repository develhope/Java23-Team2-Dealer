package com.develhope.spring.users.services;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    public UserSavedDTO createUser (UserCreatorDTO userCreatorDTO) {
        User userToRegister = userMapper.toEntityFrom(userCreatorDTO);
        userRepository.save(userToRegister);
        return userMapper.toUserSavedDTOFrom(userToRegister);
    }
}
