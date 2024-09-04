package com.example.jparemind1.domain1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jparemind1.domain1.Book;
import com.example.jparemind1.domain1.BookStore;
import com.example.jparemind1.repository1.BookRepository;
import com.example.jparemind1.repository1.BookStoreRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {


	private final BookRepository bookRepository;
	private final BookStoreRepository bookStoreRepository;
	private final EntityManager em;


	@Transactional
	public BookStore getProxyBookStore() {

		BookStore bookStore = BookStore.builder()
			.name("테스트 북스토어1")
			.build();

		Book book = Book.builder()
			.title("테스트 북1")
			.bookStore(bookStore)
			.build();

		bookStoreRepository.save(bookStore);
		bookRepository.save(book);

		// 프록시 객체를 가져오기 위해 DB에 내용을 반영하고, 1차 캐시를 지운다.
		em.flush();
		em.clear();

		// save를 하는 시점에서 book의 id가 자동으로 할당 된다.
		// 따라서 1차 캐시를 지우더라도 id는 이미 할당된 상태
		Long bookId = book.getId();

		// book를 가져오면 LAZY 전략에 의해 bookStore는 프록시 객체로 가져와진다.
		Book getBook = bookRepository.findById(bookId).orElse(null);

		BookStore getBookStore = getBook.getBookStore();

		/**
		 * 프록시 객체를 강제로 초기화한다. 따라서 실제 객체에 필요한 정보를 전부 가져온다.
		 * 북스토어와 추가로 연관된 엔티티들은 LAZY일 경우 프록시로 가져오고, EAGER라면 즉시 가져온다.
		 */
		// Hibernate.initialize(getBookStore);

		return getBookStore;

	}
}
