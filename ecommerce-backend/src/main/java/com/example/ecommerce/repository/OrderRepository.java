package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.items WHERE o.user.id = :userId AND o.isCanceled = false")
    List<Order> findOrdersWithItemsByUserId(@Param("userId") Long userId);
}
