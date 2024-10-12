package com.example.jparemind1;

import static org.assertj.core.api.Assertions.*;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.jparemind1.domain1.Member;
import com.example.jparemind1.domain1.Team;
import com.example.jparemind1.repository1.MemberRepository;
import com.example.jparemind1.repository1.TeamRepository;

@DataJpaTest
// 실제 DB 사용 옵션, 해당 애노테이션이 없을 시 @DataJpaTest는 스프링부트의 테스트 전용 DB를 사용하게 됨
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest7 {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	TeamRepository teamRepository;


	@Test
	@DisplayName("Lazy 전략 테스트")
	void lazyTest() throws Exception {

		//given
		//when
		final Member member = memberRepository.findById(1L).orElse(null);
		final Team team = member.getTeam();

		//then
		assertThat(team).isInstanceOf(HibernateProxy.class);
		System.out.println("========= 초기화 ==========");
		Hibernate.initialize(team);
		assertThat(team).isInstanceOf(HibernateProxy.class);

	}
}
