package com.develhope.spring.users.services;

import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserResponseDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.models.UserMapper;
import com.develhope.spring.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    public UserResponseDTO createUser (UserCreatorDTO userCreatorDTO) {
        User userToRegister = userMapper.toEntity(userCreatorDTO);
        userRepository.save(userToRegister);
        return userMapper.toResponseDTO(userToRegister);
    }
}
