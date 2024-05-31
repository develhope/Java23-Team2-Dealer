package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;


}
