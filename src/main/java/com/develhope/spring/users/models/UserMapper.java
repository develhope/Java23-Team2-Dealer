package com.develhope.spring.users.models;

import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserResponseDTO;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getSurname(), user.getRoles());
    }

    public User toEntity(UserCreatorDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());

        return user;
    }

    public UserOrderReturnerDTO toUserOrderReturnerDTO(User user) {
        return new UserOrderReturnerDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }

    public UserCreatorDTO toCreatorDTO(User user) {
        return new UserCreatorDTO(
                user.getName(),
                user.getSurname(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
