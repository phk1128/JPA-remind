package com.example.jparemind1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jparemind1.domain.MyEntity;

@Repository
public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {

}
