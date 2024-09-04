package com.example.jparemind1;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.hibernate.proxy.HibernateProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.jparemind1.domain1.BookStore;
import com.example.jparemind1.domain1.service.BookService;

@SpringBootTest
public class JpaTest6 {

	@Autowired
	private BookService bookService;

	private BookStore proxyBookStore;


	@BeforeEach
	void setUp() throws Exception {

		proxyBookStore = bookService.getProxyBookStore();

	}

	@DisplayName("LazyInitializationException 발생 성공 테스트")
	@Test
	void proxyTest1() throws Exception {
		// given

		System.out.println("proxyBookStore : " + proxyBookStore.getClass());
		System.out.println("BookStore : " + BookStore.class);

		// when

		// then

		// 프록시 북 스토어는 프록시 타입이다.
		assertThat(proxyBookStore).isInstanceOf(HibernateProxy.class);
		// 프록시 북 스토어는 북 스토어 인스턴스 타입이다.
		assertThat(proxyBookStore).isInstanceOf(BookStore.class);
		// 프록시 북 스토어의 클래스는 북스토어 클래스와 동일하지 않다.
		assertThat(proxyBookStore.getClass()).isNotEqualTo(BookStore.class);
		assertThatThrownBy(() -> proxyBookStore.getName())
			.isInstanceOf(LazyInitializationException.class);
	}

	@DisplayName("LazyInitializationException 발생 실패 테스트")
	@Test
	@Transactional
	void proxyTest2() throws Exception {
	    //given

	    //when

	    //then

		assertThat(proxyBookStore.getName()).isNotNull();
		assertThat(Hibernate.isInitialized(proxyBookStore)).isTrue();

	}
}

