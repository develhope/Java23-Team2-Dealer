package com.develhope.spring.users.services;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private void checkUserAuthorizationBy(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty() || !optionalUser.get().getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare gli utenti");
        }
    }

    public User getBy(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserSavedDTO create(UserCreatorDTO userCreatorDTO) {
        User userToRegister = userMapper.toEntity(userCreatorDTO);
        userRepository.save(userToRegister);
        return userMapper.toUserSavedDTO(userToRegister);
    }



    public void deleteUser (long userID, long userIDToDelete){
        checkUserAuthorizationBy(userID);
        User userToDelete = userRepository.findById(userIDToDelete).orElseThrow();
        userRepository.delete(userToDelete);
    }
}
