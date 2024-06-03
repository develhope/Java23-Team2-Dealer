package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserSavedDTO toUserSavedDTOFrom(User user) {
        return new UserSavedDTO(user.getName(), user.getSurname(), user.getRoles());
    }

    public UserCreatorDTO toUserCreatorDTOFrom(User user) {
        return new UserCreatorDTO(user.getName(), user.getSurname(), user.getPhoneNumber(),user.getEmail(),user.getRoles());
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
}
