package com.develhope.spring.deals.repositories;

import com.develhope.spring.deals.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
