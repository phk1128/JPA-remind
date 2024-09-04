package com.example.jparemind1.repository1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jparemind1.domain1.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
