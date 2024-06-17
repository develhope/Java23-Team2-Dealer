package com.develhope.spring.deals.repositories;

import com.develhope.spring.deals.models.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Transactional
    @Modifying
    @Query("DELETE o FROM orders o JOIN users ON o.user_id = :userId WHERE o.id = :orderId")
    void deleteByIdWhereUserIdLike(long orderId, long userId);

    Optional<Order> findByIdAndUserId(long orderId, long userId);
}
