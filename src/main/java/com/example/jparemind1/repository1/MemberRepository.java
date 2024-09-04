package com.example.jparemind1.repository1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jparemind1.domain1.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


}
