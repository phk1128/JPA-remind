package com.example.jparemind1.repository1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jparemind1.domain1.OrderBook;

public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {

}
