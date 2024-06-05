package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserResponseDTO;
import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
import com.develhope.spring.users.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getSurname(), user.getRoles());
    }

    public UserCreatorDTO toCreatorDTO(User user) {
        return new UserCreatorDTO(user.getName(), user.getSurname(), user.getPhoneNumber(),user.getEmail(),user.getRoles());
    }

    public UserUpdaterDTO toUserUpdaterDTO (User user){
        return new UserUpdaterDTO(user.getId(), user.getName(), user.getSurname(), user.getPhoneNumber(),
                user.getEmail(), user.getRoles());
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

    public User toEntity(UserUpdaterDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());

        return user;
    }
    public UserReworkedDTO toReworkedDTO (User user){
        return new UserReworkedDTO(user.getId(), user.getName(), user.getSurname(), user.getPhoneNumber(),
                user.getEmail(), user.getRoles());
    }
}
