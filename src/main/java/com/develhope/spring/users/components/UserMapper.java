package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.UserRentalReturnerDto;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.dtos.UserReworkedDTO;
import com.develhope.spring.users.dtos.UserUpdaterDTO;
import com.develhope.spring.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserUpdaterDTO toUserUpdaterDTO (User user) {
        return new UserUpdaterDTO(user.getName(), user.getSurname(), user.getUsername(), user.getPhoneNumber(),
                user.getEmail(), user.getRole());
    }
    public UserSavedDTO toUserSavedDTO(User user) {
        return new UserSavedDTO(user.getId(), user.getName(), user.getSurname(), user.getUsername(),user.getRole());
    }

    public User toEntity(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setSurname(userRegistrationDTO.getSurname());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole(userRegistrationDTO.getRoles());
        return user;
    }

    public UserRentalReturnerDto toUserRentalDTO(User user) {
        return new UserRentalReturnerDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhoneNumber());
    }

    public User toEntity(UserRentalReturnerDto userRentalReturnerDto) {
        return new User(
                userRentalReturnerDto.getId(),
                userRentalReturnerDto.getName(),
                userRentalReturnerDto.getSurname(),
                "default",
                "default",
                userRentalReturnerDto.getPhoneNumber(),
                userRentalReturnerDto.getEmail(),
                Roles.BUYER
        );
    }

    public UserOrderReturnerDTO toUserOrderReturnerDTO(User user) {
        return new UserOrderReturnerDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }

    public User toEntity(UserUpdaterDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        return user;
    }
    public UserReworkedDTO toReworkedDTO (User user){
        return new UserReworkedDTO(user.getId(), user.getName(), user.getSurname(), user.getUsername(), user.getPhoneNumber(),
                user.getEmail(),user.getRole());
    }
}
