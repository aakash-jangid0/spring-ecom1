package com.sky.spring_ecom1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.spring_ecom1.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
