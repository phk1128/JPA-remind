package com.example.jparemind1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jparemind1.domain.BookStore;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {
}
