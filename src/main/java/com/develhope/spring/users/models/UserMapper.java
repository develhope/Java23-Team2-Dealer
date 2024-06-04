package com.develhope.spring.users.models;

import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.models.exceptions.EmptyParameterException;
import com.develhope.spring.users.models.exceptions.WrongEmailFormatException;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserOrderReturnerDTO toUserOrderReturnerDTOFrom(User user) {
        return new UserOrderReturnerDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }

    public static User toUserFrom(UserCreatorDTO dto) throws EmptyParameterException, WrongEmailFormatException {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setRoles(dto.getRoles());
        return user;
    }

    public static UserCreatorDTO toUserCreatorDTOFrom(User user) {
        return new UserCreatorDTO(
                user.getName(),
                user.getSurname(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
