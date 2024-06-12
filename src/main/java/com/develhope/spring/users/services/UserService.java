package com.develhope.spring.users.services;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
import com.develhope.spring.users.models.Roles;
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

    public User getBy(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    void checkUserAuthorizationBy(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty() || !optionalUser.get().getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare gli utenti");
        }
    }

    public UserSavedDTO create(UserCreatorDTO userCreatorDTO) {
        User userToRegister = userMapper.toEntity(userCreatorDTO);
        User userSaved = userRepository.save(userToRegister);
        return userMapper.toUserSavedDTO(userSaved);
    }

    public UserReworkedDTO update(long userId, UserUpdaterDTO userUpdaterDTO) {
        checkUserAuthorizationBy(userId);
        User userToUpdate = userRepository.findById(userId).orElseThrow();
        userToUpdate.setName(userUpdaterDTO.getName());
        userToUpdate.setSurname(userUpdaterDTO.getSurname());
        userToUpdate.setEmail(userUpdaterDTO.getEmail());
        userToUpdate.setPhoneNumber(userUpdaterDTO.getPhoneNumber());
        User newUser = userRepository.save(userToUpdate);

        return userMapper.toReworkedDTO(newUser);
    }
}
