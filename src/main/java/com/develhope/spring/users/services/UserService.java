package com.develhope.spring.users.services;

import com.develhope.spring.users.components.Mapper;
import com.develhope.spring.users.dto.UserCreationDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Mapper mapper;


    public UserCreationDTO createUser (UserCreationDTO userCreationDTO) {
        userRepository.save(mapper.toUser(userCreationDTO));
        return userCreationDTO;
    }
}
