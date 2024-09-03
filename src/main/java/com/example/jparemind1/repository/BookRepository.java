package com.example.jparemind1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jparemind1.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
