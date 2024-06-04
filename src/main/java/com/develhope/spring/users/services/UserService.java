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

    public User getBy(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserSavedDTO create(UserCreatorDTO userCreatorDTO) {
        User userToRegister = userMapper.toEntity(userCreatorDTO);
        userRepository.save(userToRegister);
        return userMapper.toUserSavedDTO(userToRegister);
    }
}
