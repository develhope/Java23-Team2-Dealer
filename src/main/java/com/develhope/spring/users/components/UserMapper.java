package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.models.exceptions.EmptyParameterException;
import com.develhope.spring.users.models.exceptions.WrongEmailFormatException;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserSavedDTO toUserSavedDTOFrom(User user) {
        return new UserSavedDTO(user.getName(), user.getSurname(), user.getRoles());
    }

    public User toEntityFrom(UserCreatorDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());

        return user;
    }

    public UserOrderReturnerDTO toUserOrderReturnerDTOFrom(User user) {
        return new UserOrderReturnerDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }

    public User toUserFrom(UserCreatorDTO userCreatorDTO) {
        User user = new User();
        user.setName(userCreatorDTO.getName());
        user.setSurname(userCreatorDTO.getSurname());
        user.setPhoneNumber(userCreatorDTO.getPhoneNumber());
        user.setEmail(userCreatorDTO.getEmail());
        user.setRoles(userCreatorDTO.getRoles());
        return user;
    }

    public UserCreatorDTO toUserCreatorDTOFrom(User user) {
        return new UserCreatorDTO(
                user.getName(),
                user.getSurname(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
