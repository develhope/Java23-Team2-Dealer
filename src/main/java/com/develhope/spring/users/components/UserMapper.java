package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserSavedDTO toResponseDTO(User user) {
        return new UserSavedDTO(user.getId(), user.getName(), user.getSurname(), user.getUsername(), user.getRoles());
    }

    public BuyerRentalReturnerDto toRentalBuyerDTO(User user) {
        return new BuyerRentalReturnerDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhoneNumber());
    }

    public UserOrderReturnerDTO toUserOrderReturnerDTO(User user) {
        return new UserOrderReturnerDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }

    //toEntity();
    public User toEntity(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setSurname(userRegistrationDTO.getSurname());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRoles(userRegistrationDTO.getRoles());
        return user;
    }

    public User toEntity(BuyerRentalReturnerDto buyerRentalReturnerDto) {
        User user = new User(buyerRentalReturnerDto.getId());
        user.setName(buyerRentalReturnerDto.getName());
        user.setSurname(buyerRentalReturnerDto.getSurname());
        user.setUsername("default");
        user.setPassword("default");
        user.setPhoneNumber(buyerRentalReturnerDto.getPhoneNumber());
        user.setEmail(buyerRentalReturnerDto.getEmail());
        user.setRoles(Roles.BUYER);

        return user;
    }
}
