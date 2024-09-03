package com.example.jparemind1;

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
public class JpaTest2 {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private EntityManager em;

	@DisplayName("양방향 연관관계 테스트")
	@Test
	void jpaTest2() throws Exception {
		//given

		Team team = Team.builder()
			.name("테스트팀1")
			.build();

		// 영속성 컨텍스트에 등록, savedTeam 과 team은 동일한 객체
		Team savedTeam = teamRepository.save(team);

		Member member = Member.builder()
			.name("테스트유저1")
			.build();

		System.out.println("========= 쓰기 지연 ============");
		em.flush();
		em.clear();
		System.out.println("==============================");

		// when
		System.out.println("========= SELECT =============");
		Team getTeam = teamRepository.findById(savedTeam.getId()).orElse(null);
		System.out.println("==============================");
		System.out.println("========== 지연 로딩 ===========");
		for (Member getMember : getTeam.getMembers()) {
			System.out.println(getMember.getName());
		}
		System.out.println("==============================");

		//then
	}
}
