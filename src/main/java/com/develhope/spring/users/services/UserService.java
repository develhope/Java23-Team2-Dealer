package com.develhope.spring.users.services;

import com.develhope.spring.users.responseStatus.UserAlreadyExistException;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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
            logger.warn("Email already exists {}", userRegistrationDTO.getEmail());
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userRegistrationDTO.getEmail());
        }
        User user = userMapper.toEntity(userRegistrationDTO);
        User savedUser = userRepository.save(user);
        logger.info("{} Registered!", savedUser);
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
        userToUpdate.setRole(userUpdaterDTO.getRole());
        User newUser = userRepository.save(userToUpdate);

        return userMapper.toReworkedDTO(newUser);
    }

    public boolean checkIfItsUserOwnID(long userID, User userDetails) {
        return userID == userDetails.getId();
    }

    public void deleteUser(long userIDToDelete) {
        userRepository.deleteById(userIDToDelete);
    }
}
