package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserResponseDTO;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getSurname(), user.getRoles());
    }

    public User toEntity(UserCreatorDTO userCreatorDTO) {
        User user = new User();
        user.setName(userCreatorDTO.getName());
        user.setSurname(userCreatorDTO.getSurname());
        user.setPhoneNumber(userCreatorDTO.getPhoneNumber());
        user.setEmail(userCreatorDTO.getEmail());
        user.setRoles(userCreatorDTO.getRoles());
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