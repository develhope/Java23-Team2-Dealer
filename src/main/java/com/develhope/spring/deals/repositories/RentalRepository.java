package com.develhope.spring.deals.repositories;

import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.vehicles.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Rental findByVehicleId(long vehicleId);
}
