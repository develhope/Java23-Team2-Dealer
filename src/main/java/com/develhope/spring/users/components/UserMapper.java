package com.develhope.spring.users.components;

import com.develhope.spring.users.dto.UserCreatorDTO;
import com.develhope.spring.users.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserCreatorDTO toDTO(User user) {
        return new UserCreatorDTO(user.getName(), user.getSurname(), user.getPhoneNumber(), user.getEmail(), user.getRoles());
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
}
