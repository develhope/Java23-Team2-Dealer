package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserSavedDTO toUserSavedDTO(User user) {
        return new UserSavedDTO(user.getId(), user.getName(), user.getSurname(), user.getUsername(),user.getRoles());
    }

    public User toEntity(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setSurname(userRegistrationDTO.getSurname());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRoles(userRegistrationDTO.getRoles());
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

//    public UserRegistrationDTO toRegistrationDTO(User user) {
//        return new UserRegistrationDTO(
//                user.getName(),
//                user.getSurname(),
//                user.getUsername(),
//                user.getPassword(),
//                user.getPhoneNumber(),
//                user.getEmail(),
//                user.getRoles()
//        );
//    }
}
