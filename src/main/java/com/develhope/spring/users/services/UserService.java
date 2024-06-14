package com.develhope.spring.users.services;

import com.develhope.spring.exceptions.UserAlreadyExistException;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.controllers.RegistrationController;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User getBy(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserSavedDTO registerNewUserAccount(UserRegistrationDTO userRegistrationDTO) {
        if (emailExists(userRegistrationDTO.getEmail())) {
            logger.warn("Email già esistente " + userRegistrationDTO.getEmail());
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userRegistrationDTO.getEmail());
        }
        User user = userMapper.toEntity(userRegistrationDTO);
        User savedUser = userRepository.save(user);
        logger.info(savedUser + " Registrato");
        return userMapper.toUserSavedDTO(savedUser);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
