package com.example.jparemind1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String number;

	@OneToOne(mappedBy = "card", fetch = FetchType.LAZY)
	private Customer customer;

	@Builder
	public Card(final Long id, final String number, final Customer customer) {
		this.id = id;
		this.number = number;
		this.customer = customer;
	}


	// 연관관계 편의 메서드
	public void updateCustomer(final Customer customer) {
		customer.setCard(this);
		this.customer = customer;
	}
}
