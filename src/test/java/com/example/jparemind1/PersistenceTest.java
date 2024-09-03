package com.example.jparemind1;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.jparemind1.domain.MyEntity;
import com.example.jparemind1.repository.MyEntityRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
// 어플리케이션에서 설정한 DB를 그대로 사용, application.yml에 설정된 DB를 사용
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersistenceTest {

	@Autowired
	private MyEntityRepository repository;

	@Autowired
	private EntityManager em;

	private MyEntity myEntity1;

	private MyEntity myEntity2;

	@BeforeEach
	void setUpMyEntity() throws Exception {
		myEntity1 = MyEntity.builder()
			.id(1L)
			.name("myEntity1")
			.build();

		myEntity2 = MyEntity.builder()
			.id(null)
			.name("myEntity2")
			.build();

	}

	@DisplayName("영속성 컨텍스트 테스트")
	@Test
	void persistenceTest() throws Exception {

		// given

		// when

		// case1. DB에 존재하는 id
		System.out.println("====== SELECT O INSERT X ======");
		MyEntity savedEntity1 = repository.save(myEntity1);
		System.out.println("===============================");

		// case2. DB에 존재하지 않는 id(null)
		System.out.println("====== SELECT O INSERT O ======");
		MyEntity savedEntity2 = repository.save(myEntity2);
		System.out.println("쓰기 지연으로 INSERT 쿼리는 커밋 시점에 나감");
		System.out.println("===============================");

		// case3. 1차 캐시에 의해 셀렉문이 나가지 않는다. 동일성에 의해 myEntity1, myEntity2는 같은 객체다
		System.out.println("====== SELECT X ======");
		MyEntity myEntity1 = repository.findById(savedEntity1.getId()).orElse(null);
		MyEntity myEntity2 = repository.findById(savedEntity1.getId()).orElse(null);
		System.out.println("1차 캐시로 SELECT 쿼리는 나가지 않음");
		System.out.println("======================");

		// case4. 영속성 컨텍스트에 저장된 saveEntity1을 변경
		System.out.println("====== SELECT X UPDATE O ======");
		savedEntity1.setName("테스트2");
		System.out.println("쓰기 지연으로 UPDATE 쿼리는 커밋 시점에 나감");
		System.out.println("===============================");

		System.out.println("======== DB 반영 ==========");
		em.flush();
		assertThat(myEntity1).isEqualTo(savedEntity1);
		assertThat(myEntity2).isEqualTo(myEntity1);

	}

}
