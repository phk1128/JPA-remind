package com.example.jparemind1.repository1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jparemind1.domain1.MyEntity;

@Repository
public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {

}
