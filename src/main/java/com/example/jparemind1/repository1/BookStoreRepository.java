package com.example.jparemind1.repository1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jparemind1.domain1.BookStore;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {
}
