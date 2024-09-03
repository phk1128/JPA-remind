package com.example.jparemind1;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.jparemind1.domain.Member;
import com.example.jparemind1.domain.Team;
import com.example.jparemind1.repository.MemberRepository;
import com.example.jparemind1.repository.TeamRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest1 {


	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private EntityManager em;


	@DisplayName("단방향 연관 관계 테스트")
	@Test
	void jpaTest1() throws Exception {
	    //given

		Team team = Team.builder()
			.name("테스트팀1")
			.build();

		// 영속성 컨텍스트에 등록
		Team savedTeam = teamRepository.save(team);

		Member member = Member.builder()
			.name("테스트유저1")
			.team(savedTeam)
			.build();

		// 영속성 컨텍스트에 등록
		Member savedMember = memberRepository.save(member);

		//when

		// Team은 영속성 컨텍스트에 등록되어 있으므로, 1차 캐시에서 가져옴 SELECT 쿼리 X
		Team getTeam = savedMember.getTeam();
		getTeam.setName("테스트팀2");

		System.out.println("======= 쓰기 지연 =========");
		em.flush();

	    //then
		assertThat(savedTeam.getName()).isEqualTo("테스트팀2");
	}


}
