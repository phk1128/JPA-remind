package com.example.jparemind1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jparemind1.domain.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
