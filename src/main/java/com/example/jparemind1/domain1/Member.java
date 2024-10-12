package com.example.jparemind1.domain1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "member_id")
	private Long id;

	private String name;


	// Join Column을 기준으로 Team의 객체를 찾아오게 된다.
	// 만약 Member 테이블에 team_id가 null 이면 Team 객체도 null이다.
	// @ManyToOne은 Fetch 기본 전략이 EAGER
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")
	private Team team;

	@Builder
	public Member(final Long id, final String name, final Team team) {
		this.team = team;
		this.id= id;
		this.name = name;
	}


}
