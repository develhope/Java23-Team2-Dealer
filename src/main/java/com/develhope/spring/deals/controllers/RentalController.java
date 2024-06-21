package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dtos.*;
import com.develhope.spring.deals.services.RentalService;
import com.develhope.spring.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RentalSavedDTO create(@RequestBody RentalCreatorDTO rentalCreatorDTO) {
        return rentalService.create(rentalCreatorDTO);
    }

    @Secured({"ADMIN", "SALESPERSON"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{rentalId}")
    public RentalReworkedDTO update(@PathVariable long rentalId, @RequestBody RentalUpdaterDTO rentalUpdaterDTO) {
        return rentalService.update(rentalId, rentalUpdaterDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<RentalGetterDTO> loadByUserId(@AuthenticationPrincipal User userDetails, @RequestParam int page, @RequestParam int size) {
        return rentalService.getByUserId(userDetails, page, size);
    }
}