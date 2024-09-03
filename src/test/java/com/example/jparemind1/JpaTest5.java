package com.example.jparemind1;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.jparemind1.domain.Card;
import com.example.jparemind1.domain.Customer;
import com.example.jparemind1.repository.CardRepository;
import com.example.jparemind1.repository.CustomerRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class JpaTest5 {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EntityManager em;

	@DisplayName("@OneToOne 양방향 테스트")
	@Test
	void oneToOneTest() throws Exception {
		//given

		Card card = Card.builder()
			.number("1234")
			.build();

		Customer customer = Customer.builder()
			.card(card)
			.name("테스트고객")
			.build();

		Card card1 = Card.builder()
			.number("4321")
			.build();

		Customer customer1 = Customer.builder()
			.card(card1)
			.name("테스트고객1")
			.build();

		//when
		card.updateCustomer(customer);

		cardRepository.save(card);
		customerRepository.save(customer);
		cardRepository.save(card1);
		customerRepository.save(customer1);

		/**
		 * DB에 영속성 컨텍스트에 쌓인 쿼리반영, 1차 캐시 지우기
		 */
		em.flush();
		em.clear();

		System.out.println("=== 모든 카드 조회시 고객도 함께 가져온다 ===");
		List<Card> cards = cardRepository.findAll();
		System.out.println("====================================");

		System.out.println("=========== 추가적인 SELECT 쿼리가 실행안된다 ============");
		for (Card c : cards) {
			System.out.println(c.getCustomer().getName());
		}
		System.out.println("====================================================");

		/**
		 * 위에서 카드를 가져온것이 1차 캐시에 저장이 되어있다.
		 * 따라서 1차 캐시를 비우지 않으면 고객에서 카드를 조회할때 1차 캐시에서 가져오기때문에 SELECT쿼리가 발생하지 않는다.
		 */
		em.clear();

		System.out.println("=== 모든 고객 조회시 고객만 가져온다 ===");
		List<Customer> customers = customerRepository.findAll();
		System.out.println("================================");

		System.out.println("=========== 추가적인 SELECT 쿼리가 실행된다. =============");
		for (Customer cus : customers) {
			System.out.println(cus.getCard().getNumber());
		}
		System.out.println("====================================================");

		//then
	}
}
