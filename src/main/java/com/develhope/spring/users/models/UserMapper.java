package com.develhope.spring.users.models;

import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public BuyerRentalReturnerDto toRentalBuyerDTO(User user) {
        return new BuyerRentalReturnerDto(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRoles());
    }

    public User toEntityFrom(BuyerRentalReturnerDto buyerRentalReturnerDto) {
        return new User(0L, buyerRentalReturnerDto.getName(), buyerRentalReturnerDto.getSurname(),buyerRentalReturnerDto.getPhoneNumber(), buyerRentalReturnerDto.getEmail(), buyerRentalReturnerDto.getRoles());
    }
}
