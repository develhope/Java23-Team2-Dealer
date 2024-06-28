package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.*;
import com.develhope.spring.users.models.Roles;
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
                user.getEmail());
    }
    public UserSavedDTO toUserSavedDTO(User user) {
        return new UserSavedDTO(user.getId(), user.getName(), user.getSurname(), user.getUsername(),user.getRole());
    }

    public User toEntity(UserRegistrationDTO userRegistrationDTO, Roles roles) {
        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setSurname(userRegistrationDTO.getSurname());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole(roles);
        return user;
    }

    public BuyerRentalReturnerDto toRentalBuyerDTO(User user) {
        return new BuyerRentalReturnerDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhoneNumber());
    }

    public User toEntity(BuyerRentalReturnerDto buyerRentalReturnerDto) {
        return new User(
                buyerRentalReturnerDto.getId(),
                buyerRentalReturnerDto.getName(),
                buyerRentalReturnerDto.getSurname(),
                "default",
                "default",
                buyerRentalReturnerDto.getPhoneNumber(),
                buyerRentalReturnerDto.getEmail(),
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

        return user;
    }
    public UserReworkedDTO toReworkedDTO (User user){
        return new UserReworkedDTO(user.getId(), user.getName(), user.getSurname(), user.getUsername(), user.getPhoneNumber(),
                user.getEmail(),user.getRole());
    }
}
