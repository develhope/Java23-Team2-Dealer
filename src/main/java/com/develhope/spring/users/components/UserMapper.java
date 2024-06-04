package com.develhope.spring.users.components;

import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.dtos.UserSavedDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserSavedDTO toUserSavedDTOFrom(User user) {
        return new UserSavedDTO(user.getName(), user.getSurname(), user.getRoles());
    }

    public User toEntityFrom(UserCreatorDTO userCreatorDTO) {
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

    public User toEntityFrom(BuyerRentalReturnerDto buyerRentalReturnerDto) {
        return new User(buyerRentalReturnerDto.getId(), buyerRentalReturnerDto.getName(), buyerRentalReturnerDto.getSurname(), buyerRentalReturnerDto.getPhoneNumber(), buyerRentalReturnerDto.getEmail(), Roles.BUYER);
    }
}
