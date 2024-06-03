package com.develhope.spring.users.services;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dto.UserCreatorDTO;
import com.develhope.spring.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    public UserCreatorDTO createUser (UserCreatorDTO userCreatorDTO) {
        userRepository.save(userMapper.toEntity(userCreatorDTO));
        return userCreatorDTO;
    }
}
