package com.develhope.spring.users.components;

import com.develhope.spring.users.dto.UserCreationDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.models.exceptions.EmptyParameterException;
import com.develhope.spring.users.models.exceptions.WrongEmailFormatException;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserCreationDTO toDTO(User user) {
        return new UserCreationDTO(user.getId(), user.getName(), user.getSurname(),
                user.getPhoneNumber(), user.getEmail(), user.getRoles());
    }

    public User toUser (UserCreationDTO userDTO) throws EmptyParameterException, WrongEmailFormatException {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getSurname(),
                userDTO.getPhoneNumber(), userDTO.getEmail(), userDTO.getRoles());
    }
}
