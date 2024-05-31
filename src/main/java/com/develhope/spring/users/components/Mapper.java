package com.develhope.spring.users.components;

import com.develhope.spring.users.dto.UserCreationDTO;
import com.develhope.spring.users.models.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserCreationDTO toDTO(User user) {
        return new UserCreationDTO(user.getName(), user.getSurname(), user.getPhoneNumber(), user.getEmail(), user.getRoles());
    }

    public User toUser(UserCreationDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());

        return user;
    }
}
