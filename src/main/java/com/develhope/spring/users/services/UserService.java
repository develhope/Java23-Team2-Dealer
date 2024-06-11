package com.develhope.spring.users.services;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.dtos.UserResponseDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    void checkUserAuthorizationBy(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty() || !optionalUser.get().getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare gli utenti");
        }
    }

    public User getBy(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserResponseDTO createUser (UserCreatorDTO userCreatorDTO) {
        User userToRegister = userMapper.toEntity(userCreatorDTO);
        User savedUser = userRepository.save(userToRegister);
        return userMapper.toResponseDTO(savedUser);
    }



    public void deleteUser (long userID, long userIDToDelete){
        checkUserAuthorizationBy(userID);
        userRepository.deleteById(userIDToDelete);
    }
}
