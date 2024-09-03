package com.example.jparemind1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jparemind1.domain.OrderBook;

public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {

}
