package com.develhope.spring.users.services;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    private void checkIfUserExists(long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("L'utente con l'ID \"" + userId + "\" non è stato trovato. Per favore," +
                    "tentare di nuovo.");
        }
    }

    public UserSavedDTO createUser (UserCreatorDTO userCreatorDTO) {
        User userToRegister = userMapper.toEntityFrom(userCreatorDTO);
        userRepository.save(userToRegister);
        return userMapper.toUserSavedDTOFrom(userToRegister);
    }

    public void deleteUser (long userID, long userIDToDelete){
        checkUserAuthorizationBy(userID);
        checkIfUserExists(userIDToDelete);
        User userToDelete = userRepository.getReferenceById(userIDToDelete);
        userRepository.delete(userToDelete);
    }
}
