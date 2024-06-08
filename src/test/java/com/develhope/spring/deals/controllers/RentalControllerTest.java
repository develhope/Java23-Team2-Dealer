package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.repositories.RentalRepository;
import com.develhope.spring.deals.services.RentalService;
import com.develhope.spring.users.services.UserService;
import com.develhope.spring.vehicles.models.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class RentalControllerTest {

    @Mock
    private RentalRepository rentalRepository;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RentalController rentalController;

    private void insertAdmin() throws Exception {
        this.mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "gabriel",
                        "surname": "dello",
                        "phoneNumber": 3467796,
                        "email": "hey@itsadmin.it",
                        "roles": "ADMIN"
                        }
                        """)).andReturn();
    }

    private void insertBuyer() throws Exception {
        this.mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "gigi",
                        "surname": "dello",
                        "phoneNumber": 3467796,
                        "email": "hey@itme.it",
                        "roles": "BUYER"
                        }
                        """)).andReturn();
    }

    private void insertVehicle() throws Exception {
        this.mockMvc.perform(post("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                        "vehicleType": "CAR",
                        "brand": "fiorino",
                        "model": "top del top",
                        "displacement": 4,
                        "color": "WHITE",
                        "power": 9999,
                        "gear": "MANUAL",
                        "registrationYear": 1700,
                        "powerSupply": "DIESEL",
                        "originalPrice": 54000.00,
                        "discountedPrice": null,
                        "usedFlag": "NEW",
                        "marketStatus": "AVAILABLE",
                        "discountedFlag": false,
                        "engine": "Al plasma"
                         }
                        """)).andReturn();
    }

    private void insertVehicleNotAvailable() throws Exception {
        this.mockMvc.perform(post("/v1/vehicles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                        "vehicleType": "CAR",
                        "brand": "fiorino",
                        "model": "top del top",
                        "displacement": 4,
                        "color": "WHITE",
                        "power": 9999,
                        "gear": "MANUAL",
                        "registrationYear": 1700,
                        "powerSupply": "DIESEL",
                        "originalPrice": 54000.00,
                        "discountedPrice": null,
                        "usedFlag": "NEW",
                        "marketStatus": "NOTAVAILABLE",
                        "discountedFlag": false,
                        "engine": "Al plasma"
                         }
                        """)).andReturn();
    }

    @Test
    void createRental_successfulTest() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "totalCost": 80.00,
                        "paid": true,
                        "vehicle": {
                                    "id": 1
                                    },
                        "buyer": {
                                 "id": 1
                                 }
                        }
                        """)).andReturn();
    }

    @Test
    void createRental_vehicleIsNotAvailableAndExceptionIsThrown() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicleNotAvailable();
        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andExpect(status().isConflict());
    }

    @Test
    void createRental_overlappingDatesAndExceptionIsThrown() throws Exception {
        insertAdmin();
        insertBuyer();
        insertVehicle();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-03",
                        "endDate": "2024-06-05",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andReturn();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-06",
                        "endDate": "2024-06-09",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andReturn();

        this.mockMvc.perform(post("/v1/rentals").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        "startDate": "2024-06-04",
                        "endDate": "2024-06-10",
                        "dailyCost": 40.00,
                        "paid": true,
                        "vehicleId": 1,
                        "userId": 2
                        }
                        """)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    @Mock
    RentalService rentalService;
    @Test
    public void testDeleteRental() {
        // Setup
        long rentalId = 1L;
        Rental rental = new Rental();
        rental.setId(rentalId);
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));

        // Execution
        rentalService.deleteRental(rentalId);

        // Verification
        verify(rentalRepository, times(1)).findById(rentalId);
        verify(rentalRepository, times(1)).delete(rental);
    }

}
