package com.develhope.spring.deals.repositories;

import com.develhope.spring.deals.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Collection<Rental> findByVehicleId(long vehicleId);
}
