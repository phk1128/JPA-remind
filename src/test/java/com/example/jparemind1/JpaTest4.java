package com.example.jparemind1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jparemind1.domain1.Card;
import com.example.jparemind1.domain1.Customer;
import com.example.jparemind1.repository1.CardRepository;
import com.example.jparemind1.repository1.CustomerRepository;

import jakarta.persistence.EntityManager;

@SpringBootTest
public class JpaTest4 {


	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EntityManager em;


	@DisplayName("@OneToOne 단방향 관계 테스트")
	@Test
	void oneToManyTest() throws Exception {
	    //given
		Customer customer = Customer.builder()
			.name("테스트고객")
			.build();

		Card card = Card.builder()
			.number("1234")
			.customer(customer)
			.build();
		//when

		customerRepository.save(customer);
		cardRepository.save(card);

	    //then
	}
}
