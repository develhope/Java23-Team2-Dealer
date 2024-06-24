package com.develhope.spring.deals.repositories;

import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Collection<Rental> findByVehicleId(long vehicleId);


    Collection<Rental> findByUserId(long userId);

    Page<Rental> findByUserId(long userId, Pageable pageable);

    Optional<Rental> findByIdAndUserId(long rentalId, long userId);
}
