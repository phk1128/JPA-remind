package com.example.jparemind1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.jparemind1.domain.Book;
import com.example.jparemind1.domain.BookStore;
import com.example.jparemind1.repository.BookRepository;
import com.example.jparemind1.repository.BookStoreRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class JpaTest3 {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookStoreRepository bookStoreRepository;

	@Autowired
	private EntityManager em;

	@DisplayName("@OneToMany 단방향 테스트")
	@Test
	void oneToManyTest() throws Exception {
	    //given

		Book book = Book.builder()
			.title("테스트북")
			.author("테스트저자")
			.build();

		BookStore bookStore = BookStore.builder()
			.name("테스트북스토어")
			.build();

		bookRepository.save(book);
		bookStoreRepository.save(bookStore);

		System.out.println("======= 쓰기 지연 ========");
		em.flush();

		//when

	    //then
	}

}
