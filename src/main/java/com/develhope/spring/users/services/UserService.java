package com.develhope.spring.users.services;

import com.develhope.spring.exceptions.UserAlreadyExistException;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User getBy(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserSavedDTO registerNewUserAccount(UserRegistrationDTO userRegistrationDTO) {
        if (emailExists(userRegistrationDTO.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userRegistrationDTO.getEmail());
        }
        User user = userMapper.toEntity(userRegistrationDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toUserSavedDTO(savedUser);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public UserReworkedDTO update(long userId, UserUpdaterDTO userUpdaterDTO) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userToUpdate.setName(userUpdaterDTO.getName());
        userToUpdate.setSurname(userUpdaterDTO.getSurname());
        userToUpdate.setEmail(userUpdaterDTO.getEmail());
        userToUpdate.setUsername(userUpdaterDTO.getUsername());
        userToUpdate.setPhoneNumber(userUpdaterDTO.getPhoneNumber());
        User newUser = userRepository.save(userToUpdate);

        return userMapper.toReworkedDTO(newUser);
    }
}
