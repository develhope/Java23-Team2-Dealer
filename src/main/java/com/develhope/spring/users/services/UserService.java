package com.develhope.spring.users.services;

import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserResponseDTO;
import com.develhope.spring.users.dtos.UserReworkedDTO;
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

    private void checkUserAuthorizationBy(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty() || !optionalUser.get().getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare gli utenti");
        }
    }

    public UserResponseDTO createUser (UserCreatorDTO userCreatorDTO) {
        User userToRegister = userMapper.toEntity(userCreatorDTO);
        userRepository.save(userToRegister);
        return userMapper.toResponseDTO(userToRegister);
    }

    public UserReworkedDTO update(long userId, UserUpdaterDTO userUpdaterDTO) {
        checkUserAuthorizationBy(userId);
        User userToUpdate = userMapper.toEntity(userUpdaterDTO);
        userToUpdate.setId(userUpdaterDTO.getId());

        return userMapper.toReworkedDTO(userRepository.save(userToUpdate));
    }
}
