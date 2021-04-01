package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}