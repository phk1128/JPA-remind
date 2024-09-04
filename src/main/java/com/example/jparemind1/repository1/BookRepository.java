package com.example.jparemind1.repository1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jparemind1.domain1.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
