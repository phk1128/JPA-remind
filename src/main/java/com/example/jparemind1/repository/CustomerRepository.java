package com.example.jparemind1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jparemind1.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
