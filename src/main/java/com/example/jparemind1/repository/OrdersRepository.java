package com.example.jparemind1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jparemind1.domain.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
