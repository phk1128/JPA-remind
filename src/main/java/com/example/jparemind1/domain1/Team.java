package com.example.jparemind1.domain1;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "team_id")
	private Long id;

	private String name;

	// mappedBy에는 Member에 존재하는 Team의 객체 이름을 넣어줍니다.
	// @OneToMany 는 Fetch 기본전략이 LAZY
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Member> members = new ArrayList<>();

	@Builder
	public Team(final Long id, final String name) {

		this.id = id;
		this.name = name;
	}

	/*
	연관관계 편의 메서드, Member와 Team 중 한곳에서만 선언한는것이 좋습니다.
	 */
	public void addMember(Member member) {
		member.setTeam(this);
		members.add(member);
	}

}
