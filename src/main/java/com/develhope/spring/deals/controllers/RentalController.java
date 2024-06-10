package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.dtos.RentalUpdaterDTO;
import com.develhope.spring.deals.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RentalReturnerDTO create(@RequestBody RentalCreatorDTO rentalCreatorDTO) {
        return rentalService.create(rentalCreatorDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{adminId}/{rentalId}")
    public RentalReturnerDTO update(@PathVariable long adminId, @PathVariable long rentalId, @RequestBody RentalUpdaterDTO rentalUpdaterDTO) {
        return rentalService.update(adminId, rentalId, rentalUpdaterDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public Page<RentalReturnerDTO> loadByUserId(@PathVariable long userId, int page, int size) {
        return rentalService.getByUserId(userId, page, size);
    }
}
